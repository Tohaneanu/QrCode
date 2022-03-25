package jpp.qrcode.decode;

import jpp.qrcode.DataBlock;
import jpp.qrcode.ErrorCorrectionInformation;
import jpp.qrcode.reedsolomon.ReedSolomonException;

public class DataDestructurer {
    public static byte[] join(DataBlock[] blocks, ErrorCorrectionInformation errorCorrectionInformation) {

    }

    public static DataBlock[] deinterleave(byte[] data, ErrorCorrectionInformation errorCorrectionInformation) {
        DataBlock[] dataBlock = new DataBlock[errorCorrectionInformation.totalBlockCount()];

        return dataBlock;
    }

    public static byte[] destructure(byte[] data, ErrorCorrectionInformation ecBlocks) throws ReedSolomonException {
        if (data.length == ecBlocks.totalDataByteCount()) {
            DataBlock[] dataBlocks = deinterleave(data, ecBlocks);
            return join(dataBlocks, ecBlocks);
        } else throw new IllegalArgumentException("Lengths does not match");
    }
}
