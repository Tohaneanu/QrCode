package jpp.qrcode.encode;

import jpp.qrcode.DataPositions;
import jpp.qrcode.ReservedModulesMask;

public class DataInserter {
    public static void insert(boolean[][] target, ReservedModulesMask mask, byte[] data) {
        DataPositions dataPositions = new DataPositions(mask);
        int i = 0;
        do {
            target[dataPositions.i()][dataPositions.j()] = data[i] == 1;
            i++;
        } while (dataPositions.next());
    }
}
