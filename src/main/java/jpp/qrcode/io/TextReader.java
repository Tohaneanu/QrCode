package jpp.qrcode.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextReader {
    public static boolean[][] read(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        List<boolean[]> qrList = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine();
            if (line.startsWith("#") || line.isEmpty()) continue;
            line = line.replaceAll("\\s+", "");
            if (!line.matches("[01]+")) throw new IOException();
            Boolean[] bools = line.chars().mapToObj((c) -> (char) c == '1').toArray(Boolean[]::new);
            boolean[] primitives = new boolean[bools.length];
            int index = 0;
            for (Boolean obj : bools) {
                primitives[index] = obj;
                index++;
            }
            qrList.add(primitives);
        }

        if (qrList.isEmpty()) throw new IOException();
        return qrList.toArray(new boolean[qrList.size()][]);
    }
}
