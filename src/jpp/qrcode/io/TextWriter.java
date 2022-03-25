package jpp.qrcode.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class TextWriter {
    public static void write(OutputStream stream, boolean[][] data) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream));
        for (boolean[] row : data) {
            for (boolean b : row) {
                String value = b ? "1" : "0";
                bw.write(value);
                bw.write(" ");
            }
            bw.newLine();
        }
        bw.close();
    }
}

