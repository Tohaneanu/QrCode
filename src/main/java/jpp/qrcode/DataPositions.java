package jpp.qrcode;

public class DataPositions {
    private final ReservedModulesMask mask;
    private int i;
    private int j;
    private boolean up = true;
    private boolean right = true;
    private int count = 0;

    public DataPositions(ReservedModulesMask mask) {
        this.mask = mask;
        this.i = mask.size() - 1;
        this.j = mask.size() - 1;
    }

    public int i() {
        return this.i;
    }

    public int j() {
        return this.j;
    }

    public boolean next() {
        if (i == mask.size() - 9 && j == 0) return false;
        if (i == mask.size() - 12 && j == 0 && mask.size() >= 45) return false;
        if (up) {
            if (right) {
                j--;
                right = false;
            } else {
                if (mask.size() >= 45 && i == 7 && j == mask.size() - 10) {
                    i = 0;
                    j = mask.size() - 12;
                    right = false;
                    up = false;
                } else if (i == 9 && (j >= mask.size() - 8 || j <= 8) && j >= 0) {
                    if (count < 2) {
                        count++;
                        if (j == 7) {
                            j = 5;
                        } else {
                            j--;
                        }
                    } else {
                        count = 0;
                        up = false;
                        right = true;
                        i++;
                        j++;
                    }
                } else {

                    if (i == 0) {
                        if (count < 2) {
                            count++;
                            j--;
                        } else {
                            count = 0;
                            up = false;
                            right = true;
                            i++;
                            j++;
                        }
                    } else if (i == 7) {
                        if (mask.isReserved(i - 1, j + 1) && mask.isReserved(i - 2, j + 1))
                            i -= 2;
                        else {
                            right = true;
                            i = i - 2;
                            j++;
                        }

                    } else if (mask.isReserved(i - 1, j + 1) && mask.isReserved(i - 1, j)) {
                        right = true;
                        i = i - 6;
                        j++;
                    } else if (mask.isReserved(i - 1, j + 1) && !mask.isReserved(i - 1, j)) {
                        right = false;
                        i--;

                    } else {
                        right = true;
                        i--;
                        j++;
                    }
                }
            }
        } else {
            if ((i == mask.size() - 9 && j <= 5 && j >= 2) || (i == mask.size() - 1 && j >= 11 && j <= mask.size() - 3)) {
                if (count < 3) {
                    count++;
                    j--;
                } else {
                    count = 0;
                    up = true;
                    right = true;
                    i--;
                    j++;
                }
            } else if (i == mask.size() - 1 && (j == 10 || j == 9)) {
                if (count < 1) {
                    count++;
                    j--;
                } else {
                    count = 0;
                    i = mask.size() - 9;
                    j--;
                }
            } else if (i == mask.size() - 9 && (j == 8 || j == 7)) {
                if (count < 1) {
                    count++;
                    j--;
                } else {
                    count = 0;
                    right = true;
                    up = true;
                    i--;
                    j++;
                }
            } else if (i == mask.size() - 9 && j == 1) {
                j--;
            } else {
                if (right) {
                    j--;
                    right = false;
                } else {
                    if (mask.size() >= 45 && i == mask.size() - 12 && j <= 4 && !right) {
                        if(j==2){
                            right=true;
                            up=true;
                            i--;
                            j++;
                        }else
                        j--;
                    } else if (mask.isReserved(i + 1, j + 1) && mask.isReserved(i + 1, j) && i + 1 != 6) {
                        i += 6;
                        j++;
                        right = true;
                    } else if (i == 5) {
                        if (mask.isReserved(i + 2, j + 1)) {
                            i += 2;
                        } else {
                            i += 2;
                            j++;
                            right = true;
                        }
                    } else if (mask.isReserved(i + 1, j + 1) && !mask.isReserved(i + 1, j)) {
                        i++;
                    } else {
                        i++;
                        j++;
                        right = true;
                    }

                }
            }
        }

        return true;
    }
}
