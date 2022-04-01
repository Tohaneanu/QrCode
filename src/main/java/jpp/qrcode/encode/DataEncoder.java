package jpp.qrcode.encode;

import jpp.qrcode.ErrorCorrection;
import jpp.qrcode.Version;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class DataEncoder {
	public static DataEncoderResult encodeForCorrectionLevel(String str, ErrorCorrection level) {
		String bytes = "0100";

		int strLen = str.length();
		Version version = Version.forDataBytesCount(strLen, level);

		String cci = Integer.toBinaryString(str.length());

		if(version.number() < 10){
			while(cci.length() < 8){
				cci = '0' + cci;
			}
		} else {
			while(cci.length() < 16){
				cci = '0' + cci;
			}
		}

		bytes += cci;

		for(int i = 0; i < strLen; i++){
			String x = Integer.toBinaryString(str.charAt(i));

			while(x.length() < 8){
				x = '0' + x;
			}

			bytes += x;
		}

		bytes += "0000";

		int bytesLen = bytes.length();

		int resultLen = bytesLen / 8;
		int rest = resultLen % 8;

		if(rest % 8 != 0) {
			resultLen += rest;
			bytesLen += 8 * rest;
		}

		int addBytes = rest;

		while(addBytes > 0){
			bytes += "11101100";
			addBytes--;
			if(addBytes > 0) {
				bytes += "00010001";
				addBytes--;
			}
		}

		byte[] result = new byte[resultLen];
		int bytesIndex = 0;
		int resultIndex = 0;
		while(bytesIndex < bytesLen){
			byte aux = 0;
			for(int i = 7; i >= 0; i--){
				aux += Math.pow(2, i) * (bytes.charAt(bytesIndex) - '0');
				bytesIndex++;
			}

			result[resultIndex] = aux;
			resultIndex++;
		}


		return new DataEncoderResult(result, version.fromNumber(version.number()));
	}
}
