package jpp.qrcode.decode;

import jpp.qrcode.DataPositions;
import jpp.qrcode.ReservedModulesMask;

public class DataExtractor {
    public static byte[] extract(boolean[][] data, ReservedModulesMask mask, int byteCount) {
        if (data == null || mask == null || byteCount < 0 || data.length != data[0].length || data.length != mask.size()) {
            throw new IllegalArgumentException("length of the mask is different from the length of data");
        }
        byte[] bytes = new byte[byteCount];
        DataPositions dataPositions = new DataPositions(mask);
        int i = 0;
        int bit = 0;
        int count = 0;
        int nr = 0;
        do {
            if (i == byteCount + 1) break;
            if (count < 8) {
                nr = data[dataPositions.i()][dataPositions.j()] ? 1 : 0;
                int power = count++ - 7;
                bit += nr * Math.pow(2, Math.abs(power));
            } else {
                bytes[i] = (byte) bit;
                i++;
                nr = data[dataPositions.i()][dataPositions.j()] ? 1 : 0;
                bit = (int) (nr * Math.pow(2, 7));
                count = 1;
            }

        } while (dataPositions.next());

        return bytes;
    }
}
