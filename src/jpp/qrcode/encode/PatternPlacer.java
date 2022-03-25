package jpp.qrcode.encode;

import jpp.qrcode.Version;
import jpp.qrcode.VersionInformation;

public class PatternPlacer {
    public static void placeOrientation(boolean[][] res, Version version) {
        for (int i = 0; i < 7; i++) {
            res[0][i] = true;
            res[6][i] = true;
            res[i][0] = true;
            res[i][6] = true;
            res[i][res.length - 7] = true;
            res[i][res.length - 1] = true;
            res[0][res.length - 1 - i] = true;
            res[6][res.length - 1 - i] = true;
            res[res.length - 1][i] = true;
            res[res.length - 7][i] = true;
            res[res.length - 1 - i][0] = true;
            res[res.length - 1 - i][6] = true;
        }
        //the center of orientation models
        for (int i = 2; i < 5; i++) {
            for (int j = 2; j < 5; j++) {
                res[i][j] = true;
                res[i][res.length - 1 - j] = true;
                res[res.length - 1 - j][i] = true;
            }
        }

    }

    public static void placeTiming(boolean[][] res, Version version) {
        for (int i = 8; i <= res.length - 9; i++) {
            if (i % 2 == 0) {
                res[6][i] = true;
                res[i][6] = true;
            }
        }
    }

    public static void placeAlignment(boolean[][] res, Version version) {
        int[] alignmentPositions = version.alignmentPositions();
        for (int alignmentPosition : alignmentPositions) {
            for (int position : alignmentPositions) {
                if (!res[alignmentPosition][position]) {
                    //center of alignment models
                    res[alignmentPosition][position] = true;
                    //top and bottom contour
                    for (int k = 0; k < 5; k++) {
                        res[alignmentPosition - 2][position - 2 + k] = true;
                        res[alignmentPosition + 2][position - 2 + k] = true;
                    }
                    //left and right contour
                    for (int k = 0; k < 3; k++) {
                        res[alignmentPosition - 1 + k][position - 2] = true;
                        res[alignmentPosition - 1 + k][position + 2] = true;
                    }
                }
            }
        }
    }

    public static void placeVersionInformation(boolean[][] data, int versionInformation) {
        String binary = Integer.toBinaryString(versionInformation);

        for (int index = 0; index < binary.length(); index++) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++)
                    data[data.length - 9 - j][5 - i] = (int) binary.charAt(index) == 1;
            }
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++)
                    data[5 - i][data.length - 9 - j] =(int) binary.charAt(index) == 1;
            }
        }
    }

    public static boolean[][] createBlankForVersion(Version version) {
        boolean[][] data=new boolean[version.size()][version.size()];
        placeOrientation(data,version);
        placeTiming(data,version);
        placeOrientation(data,version);
        data[data.length - 8][8] = true;
        placeVersionInformation(data, VersionInformation.forVersion(version));
        return data;
    }
}
