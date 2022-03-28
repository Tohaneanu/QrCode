package jpp.qrcode;

import junit.framework.TestCase;

import javax.xml.crypto.Data;

public class DataPositionsTest extends TestCase {

    public void testNext() {
        Version version=Version.fromNumber(1);
        DataPositions dataPositions=new DataPositions(ReservedModulesMask.forVersion(version));
        int[][] ints=new int[version.size()][version.size()];
        int i=1;
        do {
            ints[dataPositions.i()][dataPositions.j()]=i++;
        }while (dataPositions.next());
        System.out.println();
    }
}