package jpp.qrcode.io;

import junit.framework.TestCase;

import java.io.*;

public class TextWriterTest extends TestCase {

    public void testWrite() {
        boolean[][] data = new boolean[0][];
        File file = new File("C:/Users/User/Desktop/qrcode/examples/Hallo_Q.txt");

        try (InputStream in = new FileInputStream(file)) {
            data = TextReader.read(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
        File fileWrite = new File("C:/Users/User/Desktop/qrcode/src/test/test.txt");

        try (OutputStream outputStream = new FileOutputStream(fileWrite)) {
           TextWriter.write(outputStream,data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean[][] dataReadAfterWrite = new boolean[0][];
        try (InputStream in = new FileInputStream(fileWrite)) {
            dataReadAfterWrite = TextReader.read(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(dataReadAfterWrite.length, dataReadAfterWrite[0].length);
        assertEquals(dataReadAfterWrite.length, data.length);

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                assertEquals(data[i][j], dataReadAfterWrite[i][j]);
            }
        }
    }
}