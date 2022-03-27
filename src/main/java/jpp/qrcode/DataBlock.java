package jpp.qrcode;

public class DataBlock {
    private final byte[] dataByte;
    private final byte[] correctionByte;

    public DataBlock(byte[] dataBytes, byte[] correctionBytes) {
        this.dataByte = dataBytes;
        this.correctionByte = correctionBytes;
    }


    public byte[] dataBytes() {
        return this.dataByte;
    }

    public byte[] correctionBytes() {
        return this.correctionByte;
    }
}
