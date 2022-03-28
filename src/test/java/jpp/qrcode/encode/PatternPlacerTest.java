package jpp.qrcode.encode;

import jpp.qrcode.Version;
import junit.framework.TestCase;

public class PatternPlacerTest extends TestCase {
    public void testCreateBlankForVersion() {
        boolean[][] data = PatternPlacer.createBlankForVersion(Version.fromNumber(8));
        int[][] newData = new int[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                newData[i][j] = data[i][j] ? 1 : 0;
            }
        }
    }
}