package jpp.qrcode.decode;

import jpp.qrcode.Encoding;
import jpp.qrcode.ErrorCorrection;
import jpp.qrcode.Version;

import javax.swing.*;

public class DataDecoder {
    public static Encoding readEncoding(byte[] bytes) {
        int encoding = 0;
        for (int i = 0; i < 4; i++) {
            encoding = (int) (encoding + Math.pow(bytes[i], i));
        }
        return Encoding.fromBits(encoding);
    }

    public static int readCharacterCount(byte[] bytes, int count) {
        int nr = 0;
        for (int i = 4; i <= count; i++) {
            nr = (int) (nr + Math.pow(bytes[i], i));
        }
        return nr;
    }

    public static String decodeToString(byte[] bytes, Version version, ErrorCorrection errorCorrection) {
        if (version.totalByteCount())
            throw new IllegalArgumentException("The number of bytes of this version does not match the error correction level");
        String text="";

        return text;
    }
}
