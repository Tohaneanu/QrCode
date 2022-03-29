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
        int nr=0;
        do {
            if (i == byteCount) break;
            if (count < 8) {
                nr= data[dataPositions.i()][dataPositions.j()] ? 1 : 0;
                bit +=  nr * Math.pow(2, count++);
            } else {
                bytes[i] = (byte) bit;
                i++;
                nr=data[dataPositions.i()][dataPositions.j()] ? 1 : 0;
                bit = nr;
                count = 1;
            }

        } while (dataPositions.next());

        return bytes;
    }
}
