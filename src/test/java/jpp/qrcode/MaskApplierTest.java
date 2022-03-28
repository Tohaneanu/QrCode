package jpp.qrcode;

import junit.framework.TestCase;

public class MaskApplierTest extends TestCase {

    public void testApplyTo() {
        int[][] data = {
                {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0},
                {}
        };

        boolean[][] originalData = new boolean[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                originalData[i][j] = data[i][j] == 1;
            }
        }
        MaskApplier.applyTo(originalData, MaskPattern.MASK010.maskFunction(), ReservedModulesMask.forVersion(Version.fromNumber((data.length - 17) / 4)));
        int[][] newData = new int[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                newData[i][j] = originalData[i][j] ? 1 : 0;
            }
        }
        System.out.println();
    }
}