public class Main {
/*
1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), д
олжно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException, и вывести результат расчета.
 */
    public static void main(String[] args) {

            try {
                System.out.println(sum4x4(new String[][]{   {"1", "0", "0", "0"},
                                                            {"0", "1", "0", "0"},
                                                            {"0", "0", "1", "0"},
                                                            {"0", "0", "0", "1"}} ));

                System.out.println(sum4x4(new String[][]{   {"1", "0", "0", "0"},
                                                            {"0", "1", "0", "0", "0"},
                                                            {"0", "0", "1", "0"},
                                                            {"0", "0", "0", "1"}} ));

                System.out.println(sum4x4(new String[][]{   {"1", "0", "0", "0"},
                                                            {"0", "1", "0", "0"},
                                                            {"0", "0",  "", "0"},
                                                            {"0", "0", "0", "1"}} ));

            } catch (TextDetailsException e) {
                System.err.println(e.getMessage());
            }
    }

    public static int sum4x4(String[][] strArray) throws MyArraySizeException, MyArrayDataException
    {
        int sum = 0;
        if (4 != strArray.length)
            throw new MyArraySizeException();

        for (int i = 0; i < strArray.length; i++) {
            if (4 != strArray[i].length)
                throw new MyArraySizeException();
            for (int j = 0; j < strArray[i].length; j++) {
                try {
                    sum += Integer.parseInt(strArray[i][j]);  // public static int parseInt(@NotNull String s) throws NumberFormatException
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}
