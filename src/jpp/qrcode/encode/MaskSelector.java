package jpp.qrcode.encode;

import jpp.qrcode.*;

import java.text.Format;

public class MaskSelector {
    public static void placeFormatInformation(boolean[][] res, int formatInformation) {
        throw new IllegalStateException();
    }

    public static int calculatePenaltySameColored(boolean[][] data) {
        throw new IllegalStateException();
    }

    public static int calculatePenalty2x2(boolean[][] arr) {
        throw new IllegalStateException();
    }

    public static int calculatePenaltyBlackWhite(boolean[][] arr) {
        throw new IllegalStateException();
    }

    public static int calculatePenaltyPattern(boolean[][] array) {
        throw new IllegalStateException();
    }

    public static int calculatePenaltyFor(boolean[][] data) {
        throw new IllegalStateException();
    }

    public static MaskPattern maskWithBestMask(boolean[][] data, ErrorCorrection correction, ReservedModulesMask modulesMask) {
        if (modulesMask.size() != data.length) throw new IllegalArgumentException("Mask does not match with data");
        MaskPattern[] maskPatterns = MaskPattern.values();
        int comp = Integer.MAX_VALUE;
        MaskPattern mask = MaskPattern.MASK000;
        for (MaskPattern maskPattern : maskPatterns) {
            MaskApplier.applyTo(data, maskPattern.maskFunction(), modulesMask);
            int i = calculatePenaltySameColored(data) + calculatePenalty2x2(data) + calculatePenaltyBlackWhite(data) + calculatePenaltyPattern(data) + calculatePenaltyFor(data);
            if (i < comp) {
                comp = i;
                mask = maskPattern;
            }
        }
        FormatInformation formatInformation = FormatInformation.get(correction, mask);
        MaskApplier.applyTo(data, mask.maskFunction(), modulesMask);
        int inform = formatInformation.formatInfo();
        String binary = Integer.toBinaryString(inform);
        for (int i = 0; i < 6; i++) {
            data[8][i] = (int) binary.charAt(i) == 1;
        }
        data[8][7] = (int) binary.charAt(6) == 1;
        data[8][8] = (int) binary.charAt(7) == 1;
        data[7][8] = (int) binary.charAt(8) == 1;
        int j = 9;
        for (int i = 5; i > -1; i--) {
            data[i][8] = (int) binary.charAt(j++) == 1;
        }
        for (int i = 0; i < 7; i++) {
            data[data.length - 1 - i][8] = (int) binary.charAt(i) == 1;
        }
        j = 7;
        for (int i = 7; i > -1; i--) {
            data[8][data.length - 1 - i] = (int) binary.charAt(j++) == 1;
        }

        return mask;
    }
}
