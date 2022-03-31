package jpp.qrcode;

import junit.framework.TestCase;

public class DataPositionsTest extends TestCase {

    public void testNext() {
        for (int v = 1; v < 41; v++) {
            Version version = Version.fromNumber(v);
            DataPositions dataPositions = new DataPositions(ReservedModulesMask.forVersion(version));
            int[][] ints = new int[version.size()][version.size()];
            int i = 1;
            do {
                ints[dataPositions.i()][dataPositions.j()] = i++;
            } while (dataPositions.next());
        }
    }
}