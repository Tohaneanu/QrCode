package jpp.qrcode.decode;

import jpp.qrcode.Encoding;
import jpp.qrcode.ErrorCorrection;
import jpp.qrcode.Version;

public class DataDecoder {
    public static Encoding readEncoding(byte[] bytes) {
        int temp = bytes[0];
        if (bytes[0] < 0) temp = 256 + bytes[0];
        String binary = Integer.toBinaryString(temp);
        while (binary.length() < 8) binary = '0' + binary;
        binary = binary.substring(0, 4);

        int nr = 0;
        for (int i = 0; i < binary.length(); i++) {
            nr += (binary.charAt(i) == '1' ? 1 : 0) * Math.pow(2, Math.abs(3 - i));
        }
        return Encoding.fromBits(nr);
    }

    public static int readCharacterCount(byte[] bytes, int count) {
        int nr = bytes[0];

        return nr;
    }

    public static String decodeToString(byte[] bytes, Version version, ErrorCorrection errorCorrection) {
        if (version.correctionInformationFor(errorCorrection).totalDataByteCount() != bytes.length)
            throw new IllegalArgumentException("The number of bytes of this version does not match the error correction level");
        Encoding encoding = readEncoding(bytes);
        int characterCount = 0;
        if (version.number() >= 10) characterCount = readCharacterCount(bytes, 16);
        else characterCount = readCharacterCount(bytes, 8);


        return new String(bytes);
    }
}
