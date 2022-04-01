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
        byte[] aux = new byte[bytes.length * 8];

        for (int i = 0; i < bytes.length; i++) {
            int temp = bytes[i];

            if (bytes[i] < 0) temp = 256 + bytes[i];

            String x = Integer.toBinaryString(temp);
            while (x.length() < 8) {
                x = '0' + x;
            }

            for (int j = 0; j < 8; j++)
                aux[i * 8 + j] = (byte) (x.charAt(j) - '0');

        }
        int nr = 0;
        for (int i = 4; i < 4 + count; i++) {
            nr += (int) (aux[i] * Math.pow(2, Math.abs(count - i + 3)));

        }
        return nr;
    }

    public static String decodeToString(byte[] bytes, Version version, ErrorCorrection errorCorrection) {
        if (version.correctionInformationFor(errorCorrection).totalDataByteCount() != bytes.length)
            throw new IllegalArgumentException("The number of bytes of this version does not match the error correction level");
        String result = "";
        Encoding encoding = readEncoding(bytes);
        int characterCount = 0;
        byte[] aux = new byte[bytes.length * 8];

        for (int i = 0; i < bytes.length; i++) {
            int temp = bytes[i];

            if (bytes[i] < 0) temp = 256 + bytes[i];

            String x = Integer.toBinaryString(temp);
            while (x.length() < 8) {
                x = '0' + x;
            }

            for (int j = 0; j < 8; j++)
                aux[i * 8 + j] = (byte) (x.charAt(j) - '0');
        }
        if (encoding != Encoding.BYTE)
            throw new QRDecodeException("Encoding is not BYTE!");
        if (version.number() >= 10) {
            characterCount = readCharacterCount(bytes, 16);
            int nr = 0;
            int count = 7;
            for (int i = 20; i < 8 * characterCount + 21; i++) {
                if (count > -1)
                    nr += (int) (aux[i] * Math.pow(2, Math.abs(count--)));
                else {
                    char a = (char) nr;
                    result += a;
                    count = 7;
                    nr = (int) (aux[i] * Math.pow(2, Math.abs(count--)));

                }
            }
        } else {
            characterCount = readCharacterCount(bytes, 8);
            int nr = 0;
            int count = 7;
            for (int i = 12; i < 8 * characterCount + 13; i++) {
                if (count > -1)
                    nr += (int) (aux[i] * Math.pow(2, Math.abs(count--)));
                else {
                    char a = (char) nr;
                    result += a;
                    count = 7;
                    nr = (int) (aux[i] * Math.pow(2, Math.abs(count--)));

                }
            }
        }


        return result;
    }
}
