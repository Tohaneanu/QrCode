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

    public static ReservedModulesMask forVersion(Version version) {
        int size = version.size();
        int[] alignmentPositions = version.alignmentPositions();
        boolean[][] reserved = new boolean[size][size];

        // orientation models
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                reserved[i][j] = true;
            for (int j = 0; j < 8; j++) {
                reserved[i][reserved.length - 1 - j] = true;
                reserved[reserved.length - 1 - j][i] = true;
            }
        }
        for (int alignmentPosition : alignmentPositions) {
            for (int position : alignmentPositions) {
                if (!reserved[alignmentPosition][position]) {
                    //center of alignment models
                    for (int i = 0; i < 5; i++)
                        for (int j = 0; j < 5; j++){
                            reserved[alignmentPosition - 2 + i][position - 2 + j] = true;
                }
                }
            }
        }

        for (int i = 8; i <= reserved.length - 9; i++) {
            reserved[6][i] = true;
            reserved[i][6] = true;
        }
        reserved[reserved.length - 8][8] = true;

        return new ReservedModulesMask(reserved);
    }
}
