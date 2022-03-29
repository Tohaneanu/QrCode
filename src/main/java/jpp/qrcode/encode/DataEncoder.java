package jpp.qrcode.encode;

import jpp.qrcode.ErrorCorrection;
import jpp.qrcode.Version;

import java.nio.charset.StandardCharsets;

public final class DataEncoder {
	public static DataEncoderResult encodeForCorrectionLevel(String str, ErrorCorrection level) {
		byte[] bytes=str.getBytes(StandardCharsets.UTF_8);
		//byte[]	bytes=new byte[str.length()*8];


		return new DataEncoderResult(bytes, Version.forDataBytesCount(bytes.length, level) );
	}
}
