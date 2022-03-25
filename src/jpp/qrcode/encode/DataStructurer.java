package jpp.qrcode.encode;

import jpp.qrcode.DataBlock;
import jpp.qrcode.ErrorCorrectionInformation;

public class DataStructurer {
    public static DataBlock[] split(byte[] data, ErrorCorrectionInformation errorCorrectionInformation) {
        throw new IllegalStateException();
    }

    public static byte[] interleave(DataBlock[] blocks, ErrorCorrectionInformation ecBlocks) {
        throw new IllegalStateException();
    }

    public static byte[] structure(byte[] data, ErrorCorrectionInformation ecBlocks) {
        if (ecBlocks.totalByteCount() != data.length)
            throw new IllegalArgumentException("Length of data does not match length of correction information blocks");
        DataBlock[] dataBlock = split(data, ecBlocks);
        return interleave(dataBlock, ecBlocks);
    }
}
