package com.pipe.util.array;

import com.pipe.util.MathHelper;
import org.apache.commons.lang3.Validate;

public class BitArray {

    private final int arraySize;
    private final int bitsPerEntry;
    private final long maxEntryValue;
    private final long[] longArray;

    ///////////////////////////////////////////////////////////////////////////

    public BitArray(int bitsPerEntryIn, int arraySizeIn) {
        Validate.inclusiveBetween(1L, 32L, (long) bitsPerEntryIn);
        this.arraySize = arraySizeIn;
        this.bitsPerEntry = bitsPerEntryIn;
        this.maxEntryValue = (1L << bitsPerEntryIn) - 1L;
        this.longArray = new long[MathHelper.roundUp(arraySizeIn * bitsPerEntryIn, 64) / 64];
    }

    ///////////////////////////////////////////////////////////////////////////

    /**
     * Sets the entry at the given location to the given value
     */
    public void setAt(int index, int value) {
        Validate.inclusiveBetween(0L, (long) (arraySize - 1), (long) index);
        Validate.inclusiveBetween(0L, maxEntryValue, (long) value);
        int i = index * bitsPerEntry;
        int j = i / 64;
        int k = ((index + 1) * bitsPerEntry - 1) / 64;
        int l = i % 64;
        longArray[j] = longArray[j] & ~(maxEntryValue << l) | ((long) value & maxEntryValue) << l;

        if (j != k) {
            int i1 = 64 - l;
            int j1 = bitsPerEntry - i1;
            longArray[k] = longArray[k] >>> j1 << j1 | ((long) value & maxEntryValue) >> i1;
        }
    }

    /**
     * Gets the entry at the given index
     */
    public int getAt(int index) {
        Validate.inclusiveBetween(0L, (long) (arraySize - 1), (long) index);
        int i = index * bitsPerEntry;
        int j = i / 64;
        int k = ((index + 1) * bitsPerEntry - 1) / 64;
        int l = i % 64;

        if (j == k) {
            return (int) (longArray[j] >>> l & maxEntryValue);

        } else {
            int i1 = 64 - l;
            return (int) ((longArray[j] >>> l | longArray[k] << i1) & maxEntryValue);
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    public long[] getBackingLongArray() {
        return longArray;
    }

    public int size() {
        return arraySize;
    }
}

