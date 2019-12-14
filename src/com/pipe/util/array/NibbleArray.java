package com.pipe.util.array;

/**
 * Byte array accessible in 4-bit pieces.
 */
public class NibbleArray {

    private final byte[] data;

    ///////////////////////////////////////////////////////////////////////////

    public NibbleArray() {
        this.data = new byte[2048];
    }

    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the nibble of data corresponding to the passed in x, y, z. y is at most 6 bits, z is at most 4.
     */
    public int get(int x, int y, int z) {
        return getFromIndex(getCoordinateIndex(x, y, z));
    }

    /**
     * Arguments are x, y, z, val. Sets the nibble of data at x << 11 | z << 7 | y to val.
     */
    public void set(int x, int y, int z, int value) {
        setIndex(getCoordinateIndex(x, y, z), value);
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getFromIndex(int index) {
        int i = getNibbleIndex(index);
        return isLowerNibble(index) ? data[i] & 15 : data[i] >> 4 & 15;
    }

    public void setIndex(int index, int value) {
        int i = getNibbleIndex(index);

        if (isLowerNibble(index)) {
            data[i] = (byte) (data[i] & 240 | value & 15);

        } else {
            data[i] = (byte) (data[i] & 15 | (value & 15) << 4);
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    private int getCoordinateIndex(int x, int y, int z) {
        return y << 8 | z << 4 | x;
    }

    private int getNibbleIndex(int index) {
        return index >> 1;
    }

    private boolean isLowerNibble(int index) {
        return (index & 1) == 0;
    }

    ///////////////////////////////////////////////////////////////////////////

    public byte[] getData() {
        return data;
    }
}
