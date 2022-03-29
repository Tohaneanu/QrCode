package jpp.qrcode.encode;

import jpp.qrcode.DataPositions;
import jpp.qrcode.ReservedModulesMask;

public class DataInserter {
    public static void insert(boolean[][] target, ReservedModulesMask mask, byte[] data) {
        DataPositions dataPositions = new DataPositions(mask);
        byte[] bytes = new byte[data.length * 8];

        for (int i = 0; i < data.length; i++) {
            String x = Integer.toBinaryString(data[i]);
            while (x.length() < 8) {
                x = '0' + x;
            }
            if (x.length() > 8) x = "11101100";
            for (int j = 0; j < 8; j++)
                bytes[i * 8 + j] = (byte) (x.charAt(j) - '0');

        }

        int i = 0;
        do {
            if (i == bytes.length) break;
            target[dataPositions.i()][dataPositions.j()] = bytes[i] == 1;
            i++;
        } while (dataPositions.next());
    }
}
