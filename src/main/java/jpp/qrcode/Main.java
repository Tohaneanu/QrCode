package jpp.qrcode;

import jpp.qrcode.decode.DataExtractor;
import jpp.qrcode.decode.Decoder;
import jpp.qrcode.encode.DataInserter;
import jpp.qrcode.encode.Encoder;
import jpp.qrcode.io.TextReader;
import jpp.qrcode.reedsolomon.ReedSolomonException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static jpp.qrcode.QRCode.createValidatedFromBooleans;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, ReedSolomonException {
//        Encoding encoding = Encoding.fromBits(2);
//        System.out.println(encoding);
//        int[][] data = {
//                {1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
//                {1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
//                {1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
//                {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
//                {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1},
//                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
//                {1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
//                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0},
//                {1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0},
//                {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0},
//                {0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1},
//                {1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
//                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1},
//                {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1},
//                {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0},
//                {1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
//                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1},
//                {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0}
//        };
//        boolean[][] data1 = new boolean[21][21];
//        for (int i = 0; i < data.length; i++) {
//            for (int j = 0; j < data.length; j++) {
//                data1[i][j] = data[i][j] != 0;
//                System.out.print(data1[i][j]+ " ");
//            }
//            System.out.println();
//        }
//        createValidatedFromBooleans(data1);
//        System.out.println(data.length);
//        System.out.println(data[0].length);
//        Version vrs = VersionInformation.fromBits(31892);
//        ReservedModulesMask mask = forVersion(vrs);


//        boolean[][] data = new boolean[0][];
//        File file = new File("C:/Users/User/Desktop/qrcode/examples/WueCampus_H.txt");
//
//        try (InputStream in = new FileInputStream(file)) {
//            data = TextReader.read(in);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        QRCode validatedFromBooleans = createValidatedFromBooleans(data);
//        ReservedModulesMask mask = forVersion(VersionInformation.fromBits(31892));
//        boolean[][] msk = mask.getMask();
//        int[][] test=new int[msk.length][msk.length];
//        for (int i=0; i< msk.length;i++)
//            for (int j=0;j< msk.length;j++)
//                if (msk[i][j])
//                    test[i][j]=1;
//                else
//                test[i][j]=0;
//        DataPositions poz = new DataPositions(mask);
//        int i = 0;
//        do {
//            i++;
//            test[poz.i()][poz.j()] = 1;
//            System.out.println(" I: "+ poz.i() + " J: "+ poz.j());
//        } while (poz.next());
//        byte[] data1 = {7, 17, 17, 17, 17, 17, 71,7, 7, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1};
//        System.out.println(20/3);
//
//        DataBlock[] deinterleave = DataDestructurer.deinterleave(data1, new ErrorCorrectionInformation(4, new ErrorCorrectionGroup(2, 2)));
//        byte[] test=DataDestructurer.join(deinterleave,new ErrorCorrectionInformation(4, new ErrorCorrectionGroup(2, 2)));
//        System.out.println();

//        MaskPattern maskPattern =MASK100 ;
//         maskPattern.maskFunction();
//
//       MaskApplier.applyTo(data, maskPattern.maskFunction(), new ReservedModulesMask(data));

//        byte[] test = new byte[]{43, 82, 24, 86, 56, -58, -14, 7, 46, 86, 57, 26, 52, -30, 22, 2, -46, 2, 24, 76, 56, -26, 57, 26, 16, -62, 5, 15, 24, 36, -10, 46, 92, -30, 5, 96, -9, 52, 6, 17, 26, 52, 6, 12, 7, 37, 17, 56, 17, 26, 52, 6, -10, -26, 52, -30, 20, -20, 11, -20, 11, 12};
//        DataBlock[] testBlock = DataStructurer.split(test, new ErrorCorrectionInformation(18, new ErrorCorrectionGroup[]{new ErrorCorrectionGroup(4, test.length)}));
//
//        for (int i = 0; i < testBlock.length; ++i) {
//            byte[] dataBytes = testBlock[i].dataBytes();
//
//            for (int j = 0; j < dataBytes.length; ++j) {
//                System.out.print(dataBytes[j] + " ");
//            }
//
//            System.out.println();
//        }

//        QRCode qrCode = Encoder.createFromString("Hallo", ErrorCorrection.MEDIUM);
//        System.out.println("\n" + qrCode.matrixToString());

//        byte a= (byte) 0b10000000;
//        System.out.println(a);
        //System.out.println(DataDecoder.readCharacterCount(new byte[]{0, (byte) 128}, 8));

//        QRCode qrCode = Encoder.createFromString("Hallo", ErrorCorrection.HIGH);
//        System.out.println("\n" + qrCode.matrixToString());


        boolean[][] data = new boolean[0][];
        File file = new File("C:/Users/User/Desktop/qrcode/examples/WueCampus_H.txt");

        try (InputStream in = new FileInputStream(file)) {
            data = TextReader.read(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Decoder.decodeToString(createValidatedFromBooleans(data)));

//        DataEncoderResult result = DataEncoder.encodeForCorrectionLevel("Hallo", ErrorCorrection.HIGH);
//        System.out.println(DataDecoder.decodeToString(result.bytes(), result.version(), ErrorCorrection.HIGH));


//        //check data insert and data extract!
//        boolean[][] blankForVersion = PatternPlacer.createBlankForVersion(Version.fromNumber(1));
//        int[][] check = new int[blankForVersion.length][blankForVersion.length];
//        extracted(blankForVersion, check);
//        byte[] data = {(byte) 150, (byte) 236, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
//        DataInserter.insert(blankForVersion, ReservedModulesMask.forVersion(Version.fromNumber(1)), data);
//        extracted(blankForVersion, check);
//        byte[] extract = DataExtractor.extract(blankForVersion, ReservedModulesMask.forVersion(Version.fromNumber(1)), Version.fromNumber(1).totalByteCount());
//        System.out.println();
        /////

        //deinterleave

//        for (int i=0;i< data.length;i++)
//            data[i]= (byte) i;
//        byte[] data1 = {67, 34, 54, 55, (byte) 130, 2, (byte) 246, 23, 36,210,70,86,134,2,146,23,86,36,226,38,198,118,5,82,198,86,150,6,242,230};
//        byte[] data1 = { D2, 46, 56, 86, 02, 92, 17, 56, 24, E2, 26, C6, 76, 05, 52, C6, 56, 96, 06, F2, E6, F7, F6, 07, 57, 52, E6, 46, 26, 06, 52, 86, 16, 17, E2, 57, C2, 26, 20, 26, 05, 52, EC, 52, 15, 06, 11, E2, 24, 12, EC, 07, 11, 60, 31, A3, 8F, B9, 21, FC, 7 A, 88, 4F, 44, 07, 5F, 43, AD, 9D, AD, B3, 20, 91, 14, 46, 39, 38, E9, AD, B1, 00, C9, 68, 7E, C6, 61, 7D, 08, D8, 44, 09, 2F, F1, SE, AC, AA, D3, 96, 7 A, 77, E1, 57, 20, 6 B, 98, C6, AE, 47, 5 A, 74, F2, 2F, BD, A3, C7, 23, 7 A, 227, 200, 20, 86, 206, 126, 20, 11};
//        DataBlock[] deinterleave = DataDestructurer.deinterleave(data, new ErrorCorrectionInformation(18, new ErrorCorrectionGroup(4, 15)));
        //byte[] test = DataDestructurer.join(deinterleave, new ErrorCorrectionInformation(2, new ErrorCorrectionGroup(2, 2)));
//        DataBlock[] split = DataStructurer.split(data, new ErrorCorrectionInformation(4, new ErrorCorrectionGroup(2, 15)));
//        byte[] interleave = DataStructurer.interleave(split, new ErrorCorrectionInformation(4, new ErrorCorrectionGroup(2, 15)));


        //check place format info
//        boolean[][ ] boll=new boolean[21][21];
//        MaskSelector.placeFormatInformation(boll, FormatInformation.get(ErrorCorrection.LOW,MaskPattern.MASK000).formatInfo());
//       int[][] check=new int[21][21];
//       extracted(boll,check);
        /// end check


//        boolean[][] data = {
//                { true, true, true, true, true, true, true, false, true, true, false, false, false, false, true, true, true, true, true, true, true},
//                { true, false, false, false, false, false, true, false, true, false, false, true, false, false, true, false, false, false, false, false, true},
//                { true, false, true, true, true, false, true, false, true, false, false, true, true, false, true, false, true, true, true, false, true},
//                { true, false, true, true, true, false, true, false, true, false, false, false, false, false, true, false, true, true, true, false, true},
//                { true, false, true, true, true, false, true, false, true, false, true, false, false, false, true, false, true, true, true, false, true},
//                { true, false, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, false, true},
//                { true, true, true, true, true, true, true, false, true, false, true, false, true, false, true, true, true, true, true, true, true},
//                { false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false},
//                { false, true, true, false, true, false, true, true, false, false, false, false, true, false, true, false, true, true, true, true, true},
//                { false, true, false, false, false, false, false, false, true, true, true, true, false, false, false, false, true, false, false, false, true},
//                { false, false, true, true, false, true, true, true, false, true, true, false, false, false, true, false, true, true, false, false, false},
//                { false, true, true, false, true, true, false, true, false, false, true, true, false, true, false, true, false, true, true, true, false},
//                { true, false, false, false, true, false, true, false, true, false, true, true, true, false, true, true, true, false, true, false, true},
//                { false, false, false, false, false, false, false, false, true, true, false, true, false, false, true, false, false, false, true, false, true},
//                { true, true, true, true, true, true, true, false, true, false, true, false, false, false, false, true, false, true, true, false, false},
//                { true, false, false, false, false, false, true, false, false, true, false, true, true, false, true, true, false, true, false, false, false},
//                { true, false, true, true, true, false, true, false, true, false, true, false, false, false, true, true, true, true, true, true, true},
//                { true, false, true, true, true, false, true, false, false, true, false, true, false, true, false, true, false, false, false, true, false},
//                { true, false, true, true, true, false, true, false, true, false, false, false, true, true, true, true, false, true, false, false, true},
//                { true, false, false, false, false, false, true, false, true, false, true, true, false, true, false, false, false, true, false, true, true},
//                { true, true, true, true, true, true, true, false, false, false, false, false, true, true, true, true, false, false, false, false, true}
//        };


        //System.out.println(MaskSelector.calculatePenaltyFor(data));


        //
//        Version version = Version.fromNumber(7);
//        boolean[][] matrix = new boolean[version.size()][version.size()];
//        byte[] data = new byte[]{(byte) 230, 13, 51, 90, 52, (byte) 207, (byte) 144, 28, (byte) 211, 60, 62, 21, 105, 17, 95, 79, (byte) 134, (byte) 247, (byte) 181, 49, (byte) 193, 63, 114, 29, 99, (byte) 145, 118, (byte) 240, (byte) 206, (byte) 234, 4, 24, (byte) 222, (byte) 129, 116, 8, (byte) 218, (byte) 212, 85, 71, (byte) 228, 80, (byte) 161, 104, (byte) 159, (byte) 179, 44, (byte) 247, (byte) 137, 101, (byte) 136, (byte) 173, (byte) 137, 20, (byte) 171, 30, (byte) 252, (byte) 209, (byte) 142, 78, 45, 72, (byte) 144, 70, (byte) 204, 53, 81, (byte) 132, 33, (byte) 251, (byte) 211, 8, 4, (byte) 152, (byte) 152, (byte) 225, 83, (byte) 229, 114, (byte) 248, (byte) 178, (byte) 188, 72, 81, (byte) 146, (byte) 142, (byte) 136, 118, (byte) 198, (byte) 133, (byte) 212, (byte) 251, (byte) 236, (byte) 221, (byte) 185, 45, 119, 117, 126, 52, (byte) 255, (byte) 210, (byte) 143, 6, (byte) 136, (byte) 248, 1, (byte) 215, (byte) 132, (byte) 179, (byte) 214, 116, 41, (byte) 201, 101, (byte) 163, (byte) 141, (byte) 244, 58, 76, 73, 15, (byte) 255, (byte) 157, 81, (byte) 134, 109, (byte) 193, (byte) 140, (byte) 241, 56, 48, 126, (byte) 186, (byte) 136, 110, 74, (byte) 162, (byte) 180, 118, 99, 16, (byte) 222, 75, 62, (byte) 166, (byte) 236, 116, 125, (byte) 151, (byte) 164, (byte) 244, (byte) 207, (byte) 174, 75, (byte) 162, 26, (byte) 160, (byte) 132, (byte) 151, 27, (byte) 248, (byte) 206, (byte) 152, 58, (byte) 235, (byte) 213, 91, 38, 59, 103, 21, (byte) 217, (byte) 197, (byte) 235, (byte) 241, (byte) 226, (byte) 254, (byte) 173, 84, 57, 73, 41, (byte) 231, (byte) 232, (byte) 246, 73, 28, (byte) 248, (byte) 168, 126, (byte) 219, 24, 61, 9, (byte) 168, (byte) 231, (byte) 216, 102, (byte) 243, 126, 27, 127, 95, (byte) 255, 55, (byte) 215, 46, 44, (byte) 222, (byte) 154, 92, (byte) 238, 34, (byte) 134, (byte) 199, (byte) 230, 80, 18, (byte) 209, 16, (byte) 168, (byte) 194, 53, (byte) 219, 50, (byte) 180, 26, (byte) 201, (byte) 223, 0, (byte) 188, (byte) 183, (byte) 154, (byte) 218, (byte) 152, (byte) 163, (byte) 247, (byte) 201, (byte) 141, (byte) 213, 15, (byte) 187, (byte) 242, (byte) 154, (byte) 155, 107, (byte) 223, 98, (byte) 145, 24, 44, 121, 104, (byte) 179, 32, (byte) 160, (byte) 242, (byte) 203, (byte) 132, 59, 67, (byte) 212, 61, (byte) 186, 107, (byte) 232, 9, (byte) 202, 36, 77, 19, (byte) 188, 16, 95, 33, (byte) 146, (byte) 238, (byte) 211, 63, (byte) 181, (byte) 156, 12, (byte) 143, (byte) 192, (byte) 131, 32, 101, 8, (byte) 181, 12, (byte) 139};
//        DataInserter.insert(matrix, ReservedModulesMask.forVersion(version), data);
//        byte[] extract = DataExtractor.extract(matrix, ReservedModulesMask.forVersion(version), data.length);
//        for (int i = 0; i < data.length; i++) {
//            if (data[i] != extract[i])
//                System.out.println(i + "  "  + "   " + data[i] + "  " + extract[i]);
//        }
//        System.out.println("nice");

    }

    private static void extracted(boolean[][] blankForVersion, int[][] check) {
        for (int i = 0; i < check.length; i++) {
            for (int j = 0; j < check.length; j++)
                check[i][j] = blankForVersion[i][j] ? 1 : 0;
        }
    }
}