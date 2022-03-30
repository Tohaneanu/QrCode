package jpp.qrcode;

import jpp.qrcode.decode.Decoder;
import jpp.qrcode.encode.Encoder;
import jpp.qrcode.io.TextReader;
import jpp.qrcode.reedsolomon.ReedSolomonException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static jpp.qrcode.QRCode.createValidatedFromBooleans;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, ReedSolomonException {
//        Encoding encoding = Encoding.fromBits(2);
//        System.out.println(encoding);

        QRCode qrCode = Encoder.createFromString("Hallo", ErrorCorrection.MEDIUM);
        System.out.println("\n" + qrCode.matrixToString());

        boolean[][] data = new boolean[0][];
        File file = new File("C:/Users/User/Desktop/qrcode/examples/Hallo_H.txt");

        try (InputStream in = new FileInputStream(file)) {
            data = TextReader.read(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Decoder.decodeToString(createValidatedFromBooleans(data));
    }
}