package com.pipe.world.chunk;

import com.pipe.util.array.NibbleArray;

public class ChunkSection {

    private int yBase;
    private BlockContainer data;
    private NibbleArray blocklightArray;
    private NibbleArray skylightArray;

    ///////////////////////////////////////////////////////////////////////////

    public ChunkSection(int yBase) {
        this.yBase = yBase;
        this.data = new BlockContainer(13);
        this.blocklightArray = new NibbleArray();
        this.skylightArray = new NibbleArray();

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                for (int z = 0; z < 16; z++) {
                    this.skylightArray.set(x, y, z, 0xFF);
                    this.blocklightArray.set(x, y, z, 0xFF);
                    data.set(x, y, z, 1 << 4 | 2);
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    // TODO

    ///////////////////////////////////////////////////////////////////////////

    public int getYBase() {
        return yBase;
    }

    public BlockContainer getData() {
        return data;
    }

    public NibbleArray getBlocklightArray() {
        return blocklightArray;
    }

    public NibbleArray getSkylightArray() {
        return skylightArray;
    }
}
