package jpp.qrcode;

public class ReservedModulesMask {
    private final boolean[][] mask;

    public ReservedModulesMask(boolean[][] mask) {
        this.mask = mask;
    }

    public boolean isReserved(int i, int j) {
        return mask[i][j];
    }

    public int size() {
        return this.mask.length;
    }

//    public boolean[][] getMask() {
//        return mask;
//    }

    public static ReservedModulesMask forVersion(Version version) {
        int size = version.size();
        int[] alignmentPositions = version.alignmentPositions();
        boolean[][] reserved = new boolean[size][size];

        //the outline of the orientation models
        for (int i = 0; i < 7; i++) {
            reserved[0][i] = true;
            reserved[6][i] = true;
            reserved[i][0] = true;
            reserved[i][6] = true;
            reserved[i][reserved.length - 7] = true;
            reserved[i][reserved.length - 1] = true;
            reserved[0][reserved.length - 1 - i] = true;
            reserved[6][reserved.length - 1 - i] = true;
            reserved[reserved.length - 1][i] = true;
            reserved[reserved.length - 7][i] = true;
            reserved[reserved.length - 1 - i][0] = true;
            reserved[reserved.length - 1 - i][6] = true;
        }
        //the center of orientation models
        for (int i = 2; i < 5; i++) {
            for (int j = 2; j < 5; j++) {
                reserved[i][j] = true;
                reserved[i][reserved.length - 1 - j] = true;
                reserved[reserved.length - 1 - j][i] = true;
            }
        }
        for (int alignmentPosition : alignmentPositions) {
            for (int position : alignmentPositions) {
                if (!reserved[alignmentPosition][position]) {
                    //center of alignment models
                    reserved[alignmentPosition][position] = true;
                    //top and bottom contour
                    for (int k = 0; k < 5; k++) {
                        reserved[alignmentPosition - 2][position - 2 + k] = true;
                        reserved[alignmentPosition + 2][position - 2 + k] = true;
                    }
                    //left and right contour
                    for (int k = 0; k < 3; k++) {
                        reserved[alignmentPosition - 1 + k][position - 2] = true;
                        reserved[alignmentPosition - 1 + k][position + 2] = true;
                    }
                }
            }
        }
        for (int i = 8; i <= reserved.length - 9; i++) {
            if (i % 2 == 0) {
                reserved[6][i] = true;
                reserved[i][6] = true;
            }
        }
        reserved[reserved.length - 8][8] = true;

        return new ReservedModulesMask(reserved);
    }
}
