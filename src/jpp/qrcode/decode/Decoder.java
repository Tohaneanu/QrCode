package jpp.qrcode.decode;

import jpp.qrcode.*;

public class Decoder {
	public static String decodeToString(QRCode qrCode) throws IllegalAccessException {
		boolean[][] data=qrCode.data();
		ReservedModulesMask reservedModulesMask=ReservedModulesMask.forVersion(qrCode.version());
		MaskApplier.applyTo(data,qrCode.maskPattern().maskFunction(), reservedModulesMask);
		Version version =VersionInformation.fromBits(data.length*data.length);
		byte[] extract = DataExtractor.extract(data, reservedModulesMask, version.totalByteCount());
		ErrorCorrectionInformation correctionInformation= new ErrorCorrectionInformation(version.);
		DataDestructurer.destructure(extract, )
	}
}
