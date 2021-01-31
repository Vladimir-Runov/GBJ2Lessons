import java.util.*;

public class Main {
 /*
    Урок 5. Многопоточность
    1. Необходимо написать два метода, которые делают следующее:
    1) Создают одномерный длинный массив, например: */
    static final int size = 10000000;
//  static final int h = size / 2;
    static float[] arr = new float[size];

/*  2) Заполняют этот массив единицами;
    3) Засекают время выполнения: long a = System.currentTimeMillis();
    4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
    5) Проверяется время окончания метода System.currentTimeMillis();
    6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
        Отличие первого метода от второго:
        Первый просто бежит по массиву и вычисляет значения.
        Второй разбивает массив на два массива,
            в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
            Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
  */

    public static void main(String[] args)
    {
        for (int i = 0; i < size; i++)
            arr[i] = 1;

        double fTime1 = singleThread(arr, size);
        double fTime2 = douaThread(arr, size/2 );
        System.out.printf("dT + " +  100.0 * fTime2/fTime1 + " %% %n"  );

    }

    private static double singleThread(float[] arr, int sz) {
        long msStart = System.currentTimeMillis();
        for (int i = 0; i < sz; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long xTime = System.currentTimeMillis() - msStart;
        System.out.printf("1-thread calc time: %d %n", xTime);
        return 1.0f * xTime;
    }

    private static double douaThread(float[] arr, int sz) {
        long start = System.currentTimeMillis();
        // Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.

        float[] arr1 = new float[sz];  System.arraycopy(arr, 0, arr1, 0, sz);
        float[] arr2 = new float[sz];  System.arraycopy(arr, sz,      arr2, 0, sz);

        MathThread thr1 = new MathThread("thread-1", arr1); thr1.start();
        MathThread thr2 = new MathThread("thread-2", arr2); thr2.start();

        try {
            thr1.join();
            thr2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(thr1.arr, 0, arr,    0, sz);
        System.arraycopy(thr1.arr, 0, arr, arr1.length, arr2.length);

        long xTime = System.currentTimeMillis() - start;
        System.out.printf("2-thread calc. time: %d %n", xTime);
        return 1.0f * xTime;
    }
}
