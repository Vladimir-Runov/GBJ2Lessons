public class MathThread extends Thread {
    public float[] arr;

    MathThread(String name, float[] arrInit) {
        super(name);
        this.arr = arrInit;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
