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
        byte[] dataByte;

        int dataIndex = 0;
        int blockIndex = 0;
        int newSize = noOfBytes;
        int index = 0;

        dataByte = new byte[noOfBytes];

        while(dataIndex < dataLen){
            if(index == newSize){
                dataBlocks[blockIndex] = new DataBlock(dataByte, ReedSolomon.calculateCorrectionBytes(dataByte, correctionBytePerBlock));
                blockIndex++;
                index = 0;

                if (blockIndex >= totalBlockCount - rest) {
                    newSize = noOfBytes + 1;
                } else {
                    newSize = noOfBytes;
                }

                dataByte = new byte[newSize];
            } else {
                dataByte[index] = data[dataIndex];
                dataIndex++;
                index++;
            }


        }

        dataBlocks[totalBlockCount - 1] = new DataBlock(dataByte, ReedSolomon.calculateCorrectionBytes(dataByte, correctionBytePerBlock));
        return dataBlocks;
    }

    public static byte[] interleave(DataBlock[] blocks, ErrorCorrectionInformation ecBlocks) {
        int totalBlockCount = ecBlocks.totalBlockCount();
        int resultIndex = 0;
        int totalBytes = 0;
        byte[][] dataBytes = new byte[totalBlockCount][];
        byte[][] correctionBlocks = new byte[totalBlockCount][];

        for (int i = 0; i < totalBlockCount; i++) {
            dataBytes[i] = blocks[i].dataBytes();
            correctionBlocks[i] = blocks[i].correctionBytes();
            totalBytes += dataBytes[i].length + correctionBlocks[i].length;
        }

        byte[] result = new byte[totalBytes];
        int minLen = dataBytes[0].length;
        int index = 0;

        for (int i = 0; i < minLen; i++) {
            for (int j = 0; j < totalBlockCount; j++) {
                result[resultIndex] = dataBytes[j][i];
                resultIndex++;
            }
        }

        while (minLen == dataBytes[index].length) {
            index++;
            if (index == totalBlockCount)
                break;
        }

        for (; index < totalBlockCount; index++) {
            result[resultIndex] = dataBytes[index][minLen];
            resultIndex++;
        }

        for (int i = 0; i < correctionBlocks[0].length; i++) {
            for (int j = 0; j < totalBlockCount; j++) {
                result[resultIndex] = correctionBlocks[j][i];
                resultIndex++;
            }
        }

        return result;
    }

    public static byte[] structure(byte[] data, ErrorCorrectionInformation ecBlocks) {
        if (ecBlocks.totalDataByteCount() != data.length)
            throw new IllegalArgumentException("Length of data does not match length of correction information blocks");
        DataBlock[] dataBlock = split(data, ecBlocks);
        return interleave(dataBlock, ecBlocks);
    }
}
