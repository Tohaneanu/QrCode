package jpp.qrcode.encode;

import jpp.qrcode.ErrorCorrection;
import jpp.qrcode.Version;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class DataEncoder {
	public static DataEncoderResult encodeForCorrectionLevel(String str, ErrorCorrection level) {
		Charset utf8charset = StandardCharsets.UTF_8;
		Charset iso88591charset = StandardCharsets.ISO_8859_1;

		ByteBuffer inputBuffer = ByteBuffer.wrap(str.getBytes(utf8charset));

// decode UTF-8
		CharBuffer data = utf8charset.decode(inputBuffer);

// encode ISO-8559-1
		ByteBuffer outputBuffer = iso88591charset.encode(data);
		byte[] outputData = outputBuffer.array();
		byte[] result = null;

		int cci = 8;

		Version resultVersion = Version.forDataBytesCount(outputData.length, level);

		if(resultVersion.number() > 10)
			cci = 16;

		int rest = (outputData.length * 8 + cci + 8) % 8;

		result = new byte[resultVersion.totalByteCount()];

		for(int i = 0; i < outputData.length; i++)
			result[i] = outputData[i];

		if(rest != 0){
			for(int i = outputData.length; i < outputData.length + 8 - rest; i++)
				result[i] = 0;
		}else rest=8;

		int step = 0;
		for(int i = outputData.length + 8 - rest; i < result.length; i++)
			if(step == 0) {
				result[i] = (byte)  236;
				step = 1;
			} else {
				result[i] = (byte) 17;
				step = 0;
			}

		return new DataEncoderResult(result, Version.fromNumber(resultVersion.number()));
	}
}
