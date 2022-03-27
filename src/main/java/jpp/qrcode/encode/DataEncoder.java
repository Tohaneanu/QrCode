package jpp.qrcode.encode;

import jpp.qrcode.ErrorCorrection;
import jpp.qrcode.Version;

public final class DataEncoder {
	public static DataEncoderResult encodeForCorrectionLevel(String str, ErrorCorrection level) {
		int len = str.length();
		int k = 0;
		byte[] bytes = new byte[len];

		for(int i = 0; i < len; i++) {
			String strByte = Integer.toBinaryString(str.charAt(i));
			int ByteLen = strByte.length();
			int dec = 0;
			if(ByteLen < 8) {
				while(ByteLen < 8) {
					strByte = '0' + strByte;
					ByteLen++;
				}
			}

			for(int j = 7; j >= 0; j--) {
				dec += (strByte.charAt(j) == '1' ? 1 : 0) * Math.pow(2, 7 - j);
			}

			bytes[k] = (byte) dec;
			k++;
		}

		return new DataEncoderResult(bytes, Version.forDataBytesCount(bytes.length, level) );
	}
}
