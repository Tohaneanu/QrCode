package jpp.qrcode.decode;

import jpp.qrcode.*;
import jpp.qrcode.reedsolomon.ReedSolomonException;

public class Decoder {
    public static String decodeToString(QRCode qrCode) throws IllegalAccessException, ReedSolomonException {
        boolean[][] data = qrCode.data();
        ReservedModulesMask reservedModulesMask = ReservedModulesMask.forVersion(qrCode.version());
        MaskApplier.applyTo(data, qrCode.maskPattern().maskFunction(), reservedModulesMask);
        Version version =Version.fromNumber((data.length-17)/4);
        byte[] extract = DataExtractor.extract(data, reservedModulesMask, version.totalByteCount());
        ErrorCorrection errorCorrection = QRCode.getFormatInformation(data).errorCorrection();
        ErrorCorrectionInformation errorCorrectionInformation = version.correctionInformationFor(errorCorrection);
        byte[] destructure = DataDestructurer.destructure(extract, errorCorrectionInformation);
        return DataDecoder.decodeToString(destructure, version, errorCorrection);
    }
}
