package jpp.qrcode.decode;

import jpp.qrcode.DataBlock;
import jpp.qrcode.ErrorCorrectionInformation;
import jpp.qrcode.reedsolomon.ReedSolomon;
import jpp.qrcode.reedsolomon.ReedSolomonException;

public class DataDestructurer {
    public static byte[] join(DataBlock[] blocks, ErrorCorrectionInformation errorCorrectionInformation) throws QRDecodeException {
        byte[] bytes = new byte[errorCorrectionInformation.totalDataByteCount()];
        int limit = 0;
        for (int i = 0; i < blocks.length; i++) {
            try {
                ReedSolomon.correct(blocks[i].dataBytes(), blocks[i].correctionBytes());
                byte[] correctionBytes = ReedSolomon.calculateCorrectionBytes(blocks[i].dataBytes(), blocks[i].correctionBytes().length);
                for (int j = 0; j < correctionBytes.length; j++) {
                    bytes[limit + j] = correctionBytes[j];
                }
                limit = correctionBytes.length;
            } catch (ReedSolomonException e) {
                throw new QRDecodeException("Data correction failed " + e.toString(), e.getCause());
            }

        }
        return bytes;
    }

    public static DataBlock[] deinterleave(byte[] data, ErrorCorrectionInformation errorCorrectionInformation) {
        int blockNr = errorCorrectionInformation.totalBlockCount();
        DataBlock[] dataBlocks = new DataBlock[blockNr];
        int ecBytes = errorCorrectionInformation.correctionBytesPerBlock();
        int rest = data.length % blockNr;
        int dataBytes = errorCorrectionInformation.lowerDataByteCount();
        for (int i = 0; i < dataBlocks.length - rest; i++) {
            dataBlocks[i] = new DataBlock(new byte[errorCorrectionInformation.lowerDataByteCount()], new byte[ecBytes]);
        }
        for (int i = dataBlocks.length - rest; i < dataBlocks.length; i++) {
            dataBlocks[i] = new DataBlock(new byte[errorCorrectionInformation.lowerDataByteCount() + 1], new byte[ecBytes]);
        }
        int finalOfErrorCorrection = data.length - ecBytes * blockNr;
        for (int dataIndex = data.length - 1, blockNumber = blockNr - 1, resultIndex = ecBytes - 1; dataIndex >= finalOfErrorCorrection; dataIndex--) {
            if (blockNumber > -1) {
                dataBlocks[blockNumber].correctionBytes()[resultIndex] = data[dataIndex];
                blockNumber--;
            } else {
                blockNumber = blockNr - 1;
                dataBlocks[blockNumber--].correctionBytes()[--resultIndex] = data[dataIndex];
            }
        }
        for (int dataIndex = finalOfErrorCorrection - 1, increment = 0; dataIndex >= finalOfErrorCorrection - rest; dataIndex--) {
            dataBlocks[dataBlocks.length - 1 - increment++].dataBytes()[dataBytes] = data[dataIndex];
        }
        dataBytes--;
        for (int dataIndex = finalOfErrorCorrection - rest - 1, blockDecrement = blockNr - 1; dataIndex >= 0; dataIndex--) {
            if (blockDecrement > -1) {
                dataBlocks[blockDecrement].dataBytes()[dataBytes] = data[dataIndex];
                blockDecrement--;
            } else {
                blockDecrement = blockNr - 1;
                dataBlocks[blockDecrement--].dataBytes()[--dataBytes] = data[dataIndex];
            }
        }
        return dataBlocks;
    }

    public static byte[] destructure(byte[] data, ErrorCorrectionInformation ecBlocks) throws ReedSolomonException {
        if (data.length == ecBlocks.totalByteCount()) {
            DataBlock[] dataBlocks = deinterleave(data, ecBlocks);
            return join(dataBlocks, ecBlocks);
        } else throw new IllegalArgumentException("Lengths does not match");
    }
}
