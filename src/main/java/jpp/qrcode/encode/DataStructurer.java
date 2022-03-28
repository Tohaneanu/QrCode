package jpp.qrcode.encode;

import jpp.qrcode.DataBlock;
import jpp.qrcode.ErrorCorrectionInformation;
import jpp.qrcode.reedsolomon.ReedSolomon;

public class DataStructurer {
    public static DataBlock[] split(byte[] data, ErrorCorrectionInformation errorCorrectionInformation) {
        int totalBlockCount = errorCorrectionInformation.totalBlockCount();
        int correctionBytePerBlock = errorCorrectionInformation.correctionBytesPerBlock();
        DataBlock[] dataBlocks = new DataBlock[totalBlockCount];
        int dataLen = data.length;
        int noOfBytes = dataLen / totalBlockCount;
        int rest = dataLen % totalBlockCount;
        int newSize = noOfBytes;
        byte[] dataByte = new byte[noOfBytes];

        int dataIndex;
        for(dataIndex = 0; dataIndex < totalBlockCount; ++dataIndex) {
            dataBlocks[dataIndex] = new DataBlock(new byte[noOfBytes], new byte[correctionBytePerBlock]);
        }

        dataIndex = 0;
        int dataBlockIndex = 0;

        for(int blockIndex = 0; dataIndex < dataLen; ++dataBlockIndex) {
            if (dataBlockIndex == newSize) {
                dataBlocks[blockIndex] = new DataBlock(dataByte, ReedSolomon.calculateCorrectionBytes(dataByte, correctionBytePerBlock));
                dataBlockIndex = 0;
                ++blockIndex;
                if (blockIndex == totalBlockCount - rest) {
                    newSize = noOfBytes + 1;
                    --rest;
                } else {
                    newSize = noOfBytes;
                }

                dataByte = new byte[newSize];
            }

            dataByte[dataBlockIndex] = data[dataIndex];
            ++dataIndex;
        }

        dataBlocks[totalBlockCount - 1] = new DataBlock(dataByte, ReedSolomon.calculateCorrectionBytes(dataByte, correctionBytePerBlock));
        return dataBlocks;
    }

    public static byte[] interleave(DataBlock[] blocks, ErrorCorrectionInformation ecBlocks) {
        int totalBlockCount = ecBlocks.totalBlockCount();
        int resultIndex = 0;
        int totalBytes = 0;
        byte[][] dataBytes = new byte[totalBlockCount][];

        for(int i = 0; i < totalBlockCount; ++i) {
            dataBytes[i] = blocks[i].dataBytes();
            totalBytes += dataBytes[i].length;
        }

        byte[] result = new byte[totalBytes];
        int minLen = dataBytes[0].length;

        for(int i = 0; i < minLen; ++i) {
            for(int j = 0; j < totalBlockCount; ++j) {
                result[resultIndex] = dataBytes[j][i];
                ++resultIndex;
            }
        }

        int rest = totalBlockCount - resultIndex;
        for(int i = totalBlockCount -  rest; i < totalBlockCount; i++){
            result[resultIndex] = dataBytes[i][minLen + 1];
            ++resultIndex;
        }

        return result;
    }

    public static byte[] structure(byte[] data, ErrorCorrectionInformation ecBlocks) {
        if (ecBlocks.totalByteCount() != data.length)
            throw new IllegalArgumentException("Length of data does not match length of correction information blocks");
        DataBlock[] dataBlock = split(data, ecBlocks);
        return interleave(dataBlock, ecBlocks);
    }
}