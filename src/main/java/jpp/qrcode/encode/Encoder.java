package jpp.qrcode.encode;

import jpp.qrcode.*;

public class Encoder {
    public static QRCode createFromString(String msg, ErrorCorrection correction) {
        DataEncoderResult dataEncoderResult = DataEncoder.encodeForCorrectionLevel(msg, correction); // verificat
        byte[] bytes = DataStructurer.structure(dataEncoderResult.bytes(), dataEncoderResult.version().correctionInformationFor(correction)); // verificat
        boolean[][] qrCode = PatternPlacer.createBlankForVersion(dataEncoderResult.version());
        ReservedModulesMask reservedModulesMask = ReservedModulesMask.forVersion(dataEncoderResult.version());
        DataInserter.insert(qrCode, reservedModulesMask, bytes);// verificat
        MaskPattern maskPattern = MaskSelector.maskWithBestMask(qrCode, correction, reservedModulesMask); // verificat
        return new QRCode(qrCode, dataEncoderResult.version(), maskPattern, correction);
    }
}
