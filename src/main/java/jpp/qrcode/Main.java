package jpp.qrcode;

import jpp.qrcode.decode.DataDecoder;
import jpp.qrcode.decode.DataDestructurer;
import jpp.qrcode.decode.Decoder;
import jpp.qrcode.encode.DataEncoder;
import jpp.qrcode.encode.DataEncoderResult;
import jpp.qrcode.encode.DataStructurer;
import jpp.qrcode.encode.Encoder;
import jpp.qrcode.io.TextReader;
import jpp.qrcode.reedsolomon.ReedSolomonException;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;

import static jpp.qrcode.MaskPattern.MASK100;
import static jpp.qrcode.QRCode.createValidatedFromBooleans;

import static jpp.qrcode.ReservedModulesMask.forVersion;

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
        byte a= (byte) 0b10000000;
        System.out.println(a);
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

    }
}