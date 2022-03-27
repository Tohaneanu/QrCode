package jpp.qrcode.encode;

import jpp.qrcode.Version;

public final class DataEncoderResult {
    private final byte[] bytes;
    private final Version version;

    public DataEncoderResult(byte[] bytes, Version version) {
        this.bytes = bytes;
        this.version = version;
    }

    public byte[] bytes() {
        return this.bytes;
    }

    public Version version() {
        return this.version;
    }
}
