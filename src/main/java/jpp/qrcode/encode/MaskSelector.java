package jpp.qrcode.encode;

import com.sun.jdi.AbsentInformationException;
import jpp.qrcode.*;


public class MaskSelector {
    public static void placeFormatInformation(boolean[][] res, int formatInformation) {
        String binary = Integer.toBinaryString(formatInformation);

        while (binary.length() < 15)
            binary = '0' + binary;

        for (int i = 0; i < 6; i++) {
            res[8][i] =  (binary.charAt(i) == '1');
        }
        res[8][7] = (binary.charAt(6) == '1');
        res[8][8] = (binary.charAt(7) == '1');
        res[7][8] = (binary.charAt(8) == '1');
        int j = 9;
        for (int i = 5; i > -1; i--) {
            res[i][8] = (binary.charAt(j++) == '1');
        }
        for (int i = 0; i < 7; i++) {
            res[res.length - 1 - i][8] = (binary.charAt(i) == '1');
        }
        j = 7;
        for (int i = 7; i > -1; i--) {
            res[8][res.length - 1 - i] = (binary.charAt(j++) == '1');
        }
    }

    public static int calculatePenaltySameColored(boolean[][] data) {
        int dataSize = data.length;
        int penalty = 0;
        boolean lastRow;
        boolean lastCol;

        for (int i = 0; i < dataSize; i++) {
            lastRow = data[i][0];
            lastCol = data[i][0];
            int currLenRow = 0;
            int currLenCol = 0;

            for (int j = 0; j < dataSize; j++) {
                //Horizontal
                if (data[i][j] == lastRow) {
                    currLenRow++;
                } else {
                    if (currLenRow >= 5) {
                        penalty += 3 + currLenRow - 5;
                    }
                    lastRow = data[i][j];
                    currLenRow = 1;
                }
                //Vertical
                if (data[j][i] == lastCol) {
                    currLenCol++;
                } else {
                    if (currLenCol >= 5) {
                        penalty += 3 + currLenCol - 5;
                    }

                    lastCol = data[j][i];
                    currLenCol = 1;
                }
            }

            //Horizontal
            if (currLenRow >= 5) {
                penalty += 3 + currLenRow - 5;
            }
            //Vertical
            if (currLenCol >= 5) {
                penalty += 3 + currLenCol - 5;
            }
        }

        return penalty;
    }

    public static int calculatePenalty2x2(boolean[][] arr) {
        int arrSize = arr.length;
        int penalty = 0;

        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < arrSize; j++) {
                if(i + 1 < arrSize && j + 1 < arrSize)
                    if (arr[i + 1][j] == arr[i][j])
                        if (arr[i][j + 1] == arr[i][j])
                            if (arr[i + 1][j + 1] == arr[i][j]) penalty += 3;
            }
        }


        return penalty;
    }

    private static int blackWhitePattern(String str, String pattern) {
        int index;
        int penalty = 0;
        do {
            if (pattern.length() > str.length()) break;

            index = str.indexOf(pattern);
            if (index != -1) {
                penalty += 40;
                str = str.substring(index + pattern.length());
            }
        } while (index != -1);

        return penalty;
    }

    public static int calculatePenaltyBlackWhite(boolean[][] arr) {
        int arrSize = arr.length;
        int penalty = 0;

        String[] patterns = {"00001011101", "10111010000"};

        String[] qrV = new String[arrSize];
        String[] qrH = new String[arrSize];

        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < arrSize; j++) {
                qrV[i] += (char) (arr[i][j] == true ? '1' : '0');
                qrH[i] += (char) (arr[j][i] == true ? '1' : '0');
            }
        }

        for (int i = 0; i < arrSize; i++) {
            penalty += blackWhitePattern(qrH[i], patterns[0]);
            penalty += blackWhitePattern(qrV[i], patterns[0]);

            penalty += blackWhitePattern(qrH[i], patterns[1]);
            penalty += blackWhitePattern(qrV[i], patterns[1]);
        }

        return penalty;

    }

    public static int calculatePenaltyPattern(boolean[][] array) {
        int arrSize = array.length;
        int darkModules = 0;
        int modules = arrSize * arrSize;
        int penalty;

        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < arrSize; j++) {
                if (array[i][j]) darkModules++;
            }
        }

        penalty = 10 * (Math.abs(2 * darkModules - modules) * 10  / modules);

        return penalty;
    }

    public static int calculatePenaltyFor(boolean[][] data) {
        return calculatePenaltySameColored(data) + calculatePenalty2x2(data) + calculatePenaltyBlackWhite(data) + calculatePenaltyPattern(data);
    }

    public static MaskPattern maskWithBestMask(boolean[][] data, ErrorCorrection correction, ReservedModulesMask modulesMask) {
        if (modulesMask.size() != data.length) throw new IllegalArgumentException("Mask does not match with data");
        MaskPattern[] maskPatterns = MaskPattern.values();
        int comp = Integer.MAX_VALUE;
        MaskPattern mask = MaskPattern.MASK000;
        for (MaskPattern maskPattern : maskPatterns) {
            boolean[][] copy = data.clone();
            FormatInformation formatInformation = FormatInformation.get(correction, maskPattern);
            placeFormatInformation(copy, formatInformation.formatInfo());
            MaskApplier.applyTo(copy, maskPattern.maskFunction(), modulesMask);

            int i = calculatePenaltyFor(copy);
            if (i < comp) {
                comp = i;
                mask = maskPattern;
            }
        }

        FormatInformation formatInformation = FormatInformation.get(correction, mask);
        MaskApplier.applyTo(data, mask.maskFunction(), modulesMask);

        System.out.print(mask + " ");
        System.out.println(calculatePenaltyBlackWhite(data));

        int inform = formatInformation.formatInfo();
        placeFormatInformation(data, inform);
        return mask;
    }
}
