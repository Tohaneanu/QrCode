package jpp.qrcode.encode;

import jpp.qrcode.ErrorCorrection;
import jpp.qrcode.ErrorCorrectionInformation;
import jpp.qrcode.Version;

public final class DataEncoder {
    public static DataEncoderResult encodeForCorrectionLevel(String str, ErrorCorrection level) {
        Version test = Version.fromNumber(40);
        if (test.totalByteCount() < str.length())
            throw new StringIndexOutOfBoundsException(" String index out of range: " + str.length());

        String bytes = "0100";

        int strLen = str.length();
        Version version = Version.forDataBytesCount(strLen, level);

        String cci = Integer.toBinaryString(str.length());

        if (version.number() < 10) {
            while (cci.length() < 8) {
                cci = '0' + cci;
            }
        } else {
            while (cci.length() < 16) {
                cci = '0' + cci;
            }
        }

        bytes += cci;

        for (int i = 0; i < strLen; i++) {
            String x = Integer.toBinaryString(str.charAt(i));

            while (x.length() < 8) {
                x = '0' + x;
            }

            bytes += x;
        }

        while(bytes.length() % 8 != 0){
            bytes += '0';
        }

        int bytesLen = bytes.length();

        int resultLen = bytesLen / 8;
        ErrorCorrectionInformation errorCorrectionInformation = version.correctionInformationFor(level);
        int rest = errorCorrectionInformation.totalDataByteCount() - resultLen;

        if(rest < 0) {
            version = Version.forDataBytesCount(resultLen, level);
            if(version.number() >= 10) {
                for (int i = 0; i < 8; i++)
                    bytes = bytes.substring(12);
                cci = Integer.toBinaryString(strLen);
                while (cci.length() < 16) {
                    cci = '0' + cci;
                }

                bytes = "0100" + bytes;
                bytesLen = bytes.length();
                errorCorrectionInformation = version.correctionInformationFor(level);
            }
            rest = errorCorrectionInformation.totalDataByteCount() - resultLen;
        }

        if (rest != 0) {
            resultLen += rest;
        }

        while (rest > 0) {
            bytes += "11101100";
            rest--;
            if (rest > 0) {
                bytes += "00010001";
                rest--;
            }
        }

        byte[] result = new byte[resultLen];
        int bytesIndex = 0;
        int resultIndex = 0;
        bytesLen = bytes.length();
        while (bytesIndex < bytesLen) {
            byte aux = 0;
            for (int i = 7; i >= 0; i--) {
                aux += Math.pow(2, i) * (bytes.charAt(bytesIndex) - '0');
                bytesIndex++;
            }

            result[resultIndex] = aux;
            resultIndex++;
        }


        return new DataEncoderResult(result, version.fromNumber(version.number()));
    }
}
