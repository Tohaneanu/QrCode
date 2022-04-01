package jpp.qrcode;

import jpp.qrcode.reedsolomon.ReedSolomonException;

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
//        boolean[][] data = new boolean[0][];
//        File file = new File("C:\\Users\\grecu\\IdeaProjects\\QrCode\\examples\\Hallo_H.txt");
//
//        try (InputStream in = new FileInputStream(file)) {
//            data = TextReader.read(in);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Decoder.decodeToString(createValidatedFromBooleans(data));

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
        System.out.println();


    }

    private static void extracted(boolean[][] blankForVersion, int[][] check) {
        for (int i = 0; i < check.length; i++) {
            for (int j = 0; j < check.length; j++)
                check[i][j] = blankForVersion[i][j] ? 1 : 0;
        }
    }
}