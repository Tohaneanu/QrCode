package jpp.qrcode;

import junit.framework.TestCase;

import javax.xml.crypto.Data;

public class DataPositionsTest extends TestCase {

    public void testNext() {
        Version version=Version.fromNumber(2);
        DataPositions dataPositions=new DataPositions(ReservedModulesMask.forVersion(version));
        int[][] ints=new int[version.size()][version.size()];
        int i=1;
        do {
            ints[dataPositions.i()][dataPositions.j()]=1;
        }while (dataPositions.next());
        int[] ints2 = version.alignmentPositions();
        System.out.println();
    }
}