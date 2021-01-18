public class MyArrayDataException extends TextDetailsException {
    public MyArrayDataException(int row, int col) {
        super(String.format("Ошибка в [%d, %d]", row, col));
    }
}
