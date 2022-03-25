package jpp.qrcode;

public class InvalidQRCodeException extends QRCodeException {
    public InvalidQRCodeException() {
    }

    public InvalidQRCodeException(String message) {
        super(message);
    }

    public InvalidQRCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidQRCodeException(Throwable cause) {
        super(cause);
    }

    public InvalidQRCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
