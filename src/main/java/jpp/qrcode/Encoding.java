package jpp.qrcode;


public enum Encoding {
    NUMERIC, ALPHANUMERIC, BYTE, KANJI, ECI, INVALID;

    public static Encoding fromBits(int i) {
        String result = "0b" + Integer.toBinaryString(i).toLowerCase();
        System.out.println(result);
        if (result.equals("0b1"))
            return NUMERIC;
        else if (result.equals("0b10"))
            return ALPHANUMERIC;
        else if (result.equals("0b100"))
            return BYTE;
        else if (result.equals("0b1000"))
            return KANJI;
        else if (result.equals("0b111"))
            return ECI;
        return INVALID;
    }

    public int bits() {
        if (this.equals(NUMERIC)) {
            return Integer.parseInt("1", 2);
        } else if (this.equals((ALPHANUMERIC))) {
            return Integer.parseInt("10", 2);
        } else if (this.equals((BYTE))) {
            return Integer.parseInt("100", 2);
        } else if (this.equals((KANJI))) {
            return Integer.parseInt("1000", 2);
        } else if (this.equals((ECI))) {
            return Integer.parseInt("111", 2);
        }
        return Integer.parseInt("-1");
    }
}
