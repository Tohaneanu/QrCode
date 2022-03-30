package jpp.qrcode.encode;

import jpp.qrcode.*;

public class Encoder {
	public static QRCode createFromString(String msg, ErrorCorrection correction) {
		DataEncoderResult dataEncoderResult=DataEncoder.encodeForCorrectionLevel(msg,correction);
		byte[] bytes=DataStructurer.structure(dataEncoderResult.bytes(),dataEncoderResult.version().correctionInformationFor(correction));
        boolean[][] qrCode=PatternPlacer.createBlankForVersion(dataEncoderResult.version());
		ReservedModulesMask reservedModulesMask=ReservedModulesMask.forVersion(dataEncoderResult.version());
		DataInserter.insert(qrCode,reservedModulesMask ,bytes);

//		MaskApplier.applyTo(qrCode,MaskPattern.MASK000.maskFunction(),reservedModulesMask);
//		QRCode qrCode1 = new QRCode(qrCode, dataEncoderResult.version(), MaskPattern.MASK000, ErrorCorrection.MEDIUM);
//		System.out.println(qrCode1.matrixToString());


		MaskPattern maskPattern=MaskSelector.maskWithBestMask(qrCode,correction,reservedModulesMask);
		return new QRCode(qrCode,dataEncoderResult.version(),maskPattern,correction);

	}
}
