package jpp.qrcode;

public class QRCode {

    boolean[][] matrix;
    Version version;
    MaskPattern pattern;
    ErrorCorrection correction;

    public QRCode(boolean[][] matrix, Version version, MaskPattern pattern, ErrorCorrection correction) {
        this.matrix = matrix;
        this.version = version;
        this.pattern = pattern;
        this.correction = correction;
    }

    public Version version() {
        return version;
    }

    public boolean[][] data() {
        return matrix;
    }

    public MaskPattern maskPattern() {
        return pattern;
    }

    public ErrorCorrection errorCorrection() {
        return correction;
    }

    public String matrixToString() {
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j]) {
                    char d = (char) 0x2588;
                    x.append(d).append(d);
                } else {
                    char d = (char) 0x2591;
                    x.append(d).append(d);
                }
            }
            x.append("\n");

        }
        return x.toString();
    }

    public static QRCode createValidatedFromBooleans(boolean[][] data) throws InvalidQRCodeException {
        if (data == null || data.length == 0 || data.length != data[0].length) {
            throw new InvalidQRCodeException("Matrix is null or empty or not quadratic!");
        }
        for (boolean[] datum : data) {
            if (data.length != datum.length) throw new InvalidQRCodeException("Matrix is not square!");
        }

        boolean a = false;
        int version = 0;
        for (int i = 1; i <= 40; i++) {
            if (data.length * data.length == (17 + 4 * i) * (17 + 4 * i)) {
                a = true;
                version = i;
                break;
            }
        }
        if (!a) {
            throw new InvalidQRCodeException("Matrix size does not match a valid qr code size");
        }
        boolean allCorners = true;
        for (int i = 0; i < 7; i++) {
            if (!data[0][i] || !data[6][i] || !data[i][0] || !data[i][6])
                throw new InvalidQRCodeException("Top left orientation pattern is wrong");
            if (!data[i][data.length - 7] || !data[i][data.length - 1] || !data[0][data.length - 1 - i] || !data[6][data.length - 1 - i])
                throw new InvalidQRCodeException("Top right orientation pattern is wrong");
            if (!data[data.length - 1][i] || !data[data.length - 7][i] || !data[data.length - 1 - i][0] || !data[data.length - 1 - i][6])
                throw new InvalidQRCodeException("Lower left orientation pattern is wrong");

            //check if is orientation pattern in lower right corner
            if (!data[data.length - 1][data.length - 7 + i] || !data[data.length - 7][data.length - 7 + i] || !data[data.length - 7 + i][data.length - 7] || !data[data.length - 7 + i][data.length - 1])
                allCorners = false;
        }
        for (int i = 1; i < 6; i++) {
            if (data[1][i] || data[5][i] || data[i][1] || data[i][5])
                throw new InvalidQRCodeException("Top left orientation pattern is wrong");
            if (data[i][data.length - 6] || data[i][data.length - 2] || data[1][data.length - 1 - i] || data[5][data.length - 1 - i])
                throw new InvalidQRCodeException("Top right orientation pattern is wrong");
            if (data[data.length - 2][i] || data[data.length - 6][i] || data[data.length - 1 - i][1] || data[data.length - 1 - i][5])
                throw new InvalidQRCodeException("Lower left orientation pattern is wrong");
            //check if is orientation pattern in lower right corner
            if (data[data.length - 2][data.length - 7 + i] || data[data.length - 6][data.length - 7 + i] || data[data.length - 7 + i][data.length - 6] || data[data.length - 7 + i][data.length - 2])
                allCorners = false;
        }
        for (int i = 2; i < 5; i++) {
            for (int j = 2; j < 5; j++) {
                if (!data[i][j]) throw new InvalidQRCodeException("Top left orientation pattern is wrong");
                if (!data[i][data.length - 1 - j])
                    throw new InvalidQRCodeException("Top right orientation pattern is wrong");
                if (!data[data.length - 1 - j][i])
                    throw new InvalidQRCodeException("Lower left orientation pattern is wrong");
                //check if is orientation pattern in lower right corner
                if (!data[data.length - 1 - i][data.length - 1 - j]) allCorners = false;
            }
        }
        if (allCorners) throw new InvalidQRCodeException("ALL Corners!");


        for (int i = 0; i < 8; i++) {
            if (data[7][i] || data[i][7]) throw new InvalidQRCodeException("Top left separators are wrong");
            if (data[7][data.length - 1 - i] || data[i][data.length - 8])
                throw new InvalidQRCodeException("Top right separators are wrong");
            if (data[data.length - 8][i] || data[data.length - 1 - i][7])
                throw new InvalidQRCodeException("Lower left separators are wrong");
        }
        for (int i = 8; i <= data.length - 9; i++) {
            if (i % 2 == 0) {
                if (!data[6][i]) throw new InvalidQRCodeException("The vertical sync pattern is wrong");
                if (!data[i][6]) throw new InvalidQRCodeException("The horizontal sync pattern is wrong");
            } else {
                if (data[6][i]) throw new InvalidQRCodeException("The vertical sync pattern is wrong");
                if (data[i][6]) throw new InvalidQRCodeException("The horizontal sync pattern is wrong");
            }
        }
        // dark module check
        if (!data[data.length - 8][8]) throw new InvalidQRCodeException("The dark module is white");
        //version information
        Version vrs = getVersion(data, version);
//Alignment
        if (version < 7 && vrs == null) vrs = Version.fromNumber(version);
        else if (version > 6 && vrs == null) throw new InvalidQRCodeException("Version is corrupted");
        int[] alignmentPositions = vrs.alignmentPositions();
        if (alignmentPositions.length > 0) for (int alignmentPosition : alignmentPositions) {
            for (int position : alignmentPositions) {
                if ((alignmentPosition == alignmentPositions[0] && position == alignmentPositions[0]) || (alignmentPosition == alignmentPositions[0] && position == alignmentPositions[alignmentPositions.length - 1]) || (alignmentPosition == alignmentPositions[alignmentPositions.length - 1] && position == alignmentPositions[0]))
                    continue;
                if (!data[alignmentPosition][position])
                    //center of alignment models
                    throw new InvalidQRCodeException("Alignment position! Center is wrong..");
                //top and bottom contour
                for (int k = 0; k < 5; k++) {
                    if (!data[alignmentPosition - 2][position - 2 + k] || !data[alignmentPosition + 2][position - 2 + k]) {
                        throw new InvalidQRCodeException("Alignment position! top or bottom contour is wrong..");
                    }

                }
                //left and right contour
                for (int k = 0; k < 3; k++) {
                    if (!data[alignmentPosition - 1 + k][position - 2] || !data[alignmentPosition - 1 + k][position + 2])
                        throw new InvalidQRCodeException("Alignment position! left or right contour is wrong..");
                }

            }
        }


        // make format information blocks
        FormatInformation formatInformation = getFormatInformation(data);

        return new QRCode(data, vrs, formatInformation.maskPattern(), formatInformation.errorCorrection());

    }

    private static Version getVersion(boolean[][] data, int version) {
        Version vrs = VersionInformation.fromBits(version);
        if (version > 6) {
            int inf1 = 0;
            int p = 17;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    int aux = data[data.length - 9 - j][5 - i] ? 1 : 0;
                    inf1 = (int) (inf1 + aux * Math.pow(2, p--));
                }
            }
            int inf2 = 0;
            p = 17;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    int aux = data[5 - i][data.length - 9 - j] ? 1 : 0;
                    inf2 = (int) (inf2 + aux * Math.pow(2, p--));
                }
            }
            int vrs1 = 0;

            Version aux = VersionInformation.fromBits(inf1);
            if (aux != null) vrs1 = aux.number();

            if (vrs1 == version) vrs = VersionInformation.fromBits(inf1);
            else {
                vrs = VersionInformation.fromBits(inf2);
            }

        }
        return vrs;
    }

    public static FormatInformation getFormatInformation(boolean[][] data) {
        int inf1 = 0;
        int j = 14;
        for (int i = 0; i < 6; i++) {
            int aux = data[8][i] ? 1 : 0;
            inf1 = (int) (inf1 + aux * Math.pow(2, j--));
        }
        int aux = data[8][7] ? 1 : 0;
        inf1 = (int) (inf1 + aux * Math.pow(2, 8));
        aux = data[8][8] ? 1 : 0;
        inf1 = (int) (inf1 + aux * Math.pow(2, 7));
        aux = data[7][8] ? 1 : 0;
        inf1 = (int) (inf1 + aux * Math.pow(2, 6));
        for (int i = 5; i > -1; i--) {
            aux = data[i][8] ? 1 : 0;
            inf1 = (int) (inf1 + aux * Math.pow(2, i));
        }
        int inf2 = 0;
        j = 14;
        for (int i = 0; i < 7; i++) {
            aux = data[data.length - 1 - i][8] ? 1 : 0;
            inf2 = (int) (inf1 + aux * Math.pow(2, j--));
        }
        for (int i = 7; i > -1; i--) {
            aux = data[8][data.length - 1 - i] ? 1 : 0;
            inf2 = (int) (inf1 + aux * Math.pow(2, i));
        }
        FormatInformation formatInformation;
        if (inf1 == inf2) {
            formatInformation = FormatInformation.fromBits(inf1);
        } else {
            if (FormatInformation.fromBits(inf1) != null) formatInformation = FormatInformation.fromBits(inf1);
            else formatInformation = FormatInformation.fromBits(inf2);
        }
        return formatInformation;
    }
}