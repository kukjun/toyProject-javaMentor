package enrolment.exception;

public class NotExpectMenuValueException extends NumberFormatException {
    public NotExpectMenuValueException() {
    }

    public NotExpectMenuValueException(String message) {
        super(message);
    }
}
