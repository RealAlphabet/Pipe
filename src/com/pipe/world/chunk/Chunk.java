package com.pipe.world.chunk;

import java.util.Arrays;

public class Chunk {

    private int xPos;
    private int zPos;
    private ChunkSection[] sectionsArray;
    private byte[] biomesArray;

    ///////////////////////////////////////////////////////////////////////////

    public Chunk(int xPos, int zPos) {
        this.xPos = xPos;
        this.zPos = zPos;
        this.sectionsArray = new ChunkSection[16];
        this.biomesArray = new byte[256];
        Arrays.fill(this.biomesArray, (byte) 127); // Fill void

        this.sectionsArray[9] = new ChunkSection(9);
    }

    ///////////////////////////////////////////////////////////////////////////

    // TODO

    ///////////////////////////////////////////////////////////////////////////

    public int getXPos() {
        return xPos;
    }

    public int getZPos() {
        return zPos;
    }

    public ChunkSection[] getSectionsArray() {
        return sectionsArray;
    }

    public byte[] getBiomeArray() {
        return biomesArray;
    }
}
