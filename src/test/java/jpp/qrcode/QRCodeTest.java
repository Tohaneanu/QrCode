package jpp.qrcode;

import jpp.qrcode.decode.Decoder;
import jpp.qrcode.encode.DataStructurer;
import jpp.qrcode.encode.MaskSelector;
import jpp.qrcode.io.TextReader;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;

import static org.junit.Assert.assertThrows;


public class QRCodeTest extends TestCase {

    public void testCreateValidatedFromBooleans() {
        //good
        File f = new File("C:\\Users\\grecu\\IdeaProjects\\QrCode\\examples");

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };
        MaskPattern[] maskPatterns = new MaskPattern[]{MaskPattern.MASK110, MaskPattern.MASK010, MaskPattern.MASK010, MaskPattern.MASK110, MaskPattern.MASK111, MaskPattern.MASK000, MaskPattern.MASK110, MaskPattern.MASK100, MaskPattern.MASK010, MaskPattern.MASK010, MaskPattern.MASK100, MaskPattern.MASK001, MaskPattern.MASK010, MaskPattern.MASK010, MaskPattern.MASK011};
        ErrorCorrection[] errorCorrections = new ErrorCorrection[]{ErrorCorrection.HIGH, ErrorCorrection.LOW, ErrorCorrection.LOW, ErrorCorrection.HIGH, ErrorCorrection.LOW, ErrorCorrection.MEDIUM, ErrorCorrection.QUARTILE, ErrorCorrection.HIGH, ErrorCorrection.LOW, ErrorCorrection.MEDIUM, ErrorCorrection.QUARTILE, ErrorCorrection.HIGH, ErrorCorrection.LOW, ErrorCorrection.MEDIUM, ErrorCorrection.QUARTILE};
        int[] version = new int[]{1, 17, 17, 1, 1, 1, 1, 26, 17, 19, 23, 5, 3, 4, 4};
        File[] files = f.listFiles(textFilter);
        boolean[][] data = new boolean[0][];
        for (int i = 0; i < files.length; i++) {
            try (InputStream in = new FileInputStream(files[i])) {
                data = TextReader.read(in);
                System.out.println(files[i]);
                MaskSelector.calculatePenaltyPattern(data);
                MaskSelector.calculatePenaltyBlackWhite(data);
//                QRCode qrCode = QRCode.createValidatedFromBooleans(data);
//                System.out.println(Decoder.decodeToString(qrCode));
//                assertEquals(qrCode.version.number(), version[i]);
//                assertEquals(qrCode.maskPattern(), maskPatterns[i]);
//                assertEquals(qrCode.errorCorrection(), errorCorrections[i]);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //invalid
        f = new File("C:/Users/User/Desktop/qrcode/examples/invalid");

        textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };
        File[] invalids = f.listFiles(textFilter);
        boolean[][] invalidData = new boolean[0][];
        for (int i = 0; i < invalids.length; i++) {
            try (InputStream in = new FileInputStream(invalids[i])) {
                invalidData = TextReader.read(in);
                boolean[][] finalInvalidData = invalidData;
                Throwable throwable = assertThrows(InvalidQRCodeException.class, () -> {
                    QRCode.createValidatedFromBooleans(finalInvalidData);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}