package jpp.qrcode.decode;

import jpp.qrcode.QRCodeException;

public class QRDecodeException extends QRCodeException {
    public QRDecodeException(String data_correction_failed) {
        super(data_correction_failed);
    }
    public QRDecodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
