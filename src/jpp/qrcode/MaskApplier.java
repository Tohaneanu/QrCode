package jpp.qrcode;

public class MaskApplier {
    public static void applyTo(boolean[][] data, MaskFunction mask, ReservedModulesMask reservedModulesMask) throws IllegalArgumentException {
        if (data.length != reservedModulesMask.size())
            throw new IllegalArgumentException("Length of 'data' is not equals with length of reservedModulesMask");
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (mask.mask(i, j) && !data[i][j]) {
                    data[i][j] = !data[i][j];
                }
            }
        }
    }
}
