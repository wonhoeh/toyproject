package toyproject.shop.exception;

public class StockNotEnoughException extends RuntimeException {
    public StockNotEnoughException() {
        super();
    }

    public StockNotEnoughException(String message) {
        super(message);
    }

    public StockNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockNotEnoughException(Throwable cause) {
        super(cause);
    }
}
