package jpp.qrcode.decode;

import jpp.qrcode.*;
import jpp.qrcode.reedsolomon.ReedSolomonException;

public class Decoder {
    public static String decodeToString(QRCode qrCode) throws IllegalAccessException, ReedSolomonException {
        boolean[][] data = qrCode.data();
        ReservedModulesMask reservedModulesMask = ReservedModulesMask.forVersion(qrCode.version());
        MaskApplier.applyTo(data, qrCode.maskPattern().maskFunction(), reservedModulesMask);
        Version version =qrCode.version();
        byte[] extract =  DataExtractor.extract(data, reservedModulesMask, version.totalByteCount());
        FormatInformation formatInformation = QRCode.getFormatInformation(data);
        ErrorCorrection errorCorrection = formatInformation.errorCorrection();
        ErrorCorrectionInformation errorCorrectionInformation = version.correctionInformationFor(errorCorrection);
        byte[] destructure = DataDestructurer.destructure(extract, errorCorrectionInformation);
        return DataDecoder.decodeToString(destructure, version, errorCorrection);
    }
}



//extract .. destructure