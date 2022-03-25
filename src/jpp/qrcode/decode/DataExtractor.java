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
        do {
            if (i == byteCount) break;
            bytes[i] = (byte) (data[dataPositions.i()][dataPositions.j()] ? 1 : 0);
            i++;
        } while (dataPositions.next());

        return bytes;
    }
}
