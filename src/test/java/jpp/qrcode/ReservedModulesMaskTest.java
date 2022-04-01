package jpp.qrcode;

import junit.framework.TestCase;

public class ReservedModulesMaskTest extends TestCase {

    public void testIsReserved() {
    }

    public void testSize() {
    }

    public void testForVersion() {
        ReservedModulesMask mask = ReservedModulesMask.forVersion(Version.fromNumber(7));
        boolean[][] data = new boolean[mask.size()][mask.size()];
        for (int i = 0; i < mask.size(); i++)
            for (int j = 0; j < mask.size(); j++)
                data[i][j] = mask.isReserved(i, j);
        StringBuilder x = new StringBuilder();
        int count=0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][j]) {
                    char d = (char) 0x2588;
                    x.append(d).append(d);
                } else {
                    char d = (char) 0x2591;
                    x.append(d).append(d);
                }
                count++;
            }
            x.append("\n");

        }
        System.out.println(x);
        System.out.println(count);

    }
}