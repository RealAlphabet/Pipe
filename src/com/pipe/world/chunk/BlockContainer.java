package com.pipe.world.chunk;

import com.pipe.netty.PacketBuffer;
import com.pipe.util.array.BitArray;

public class BlockContainer {

    private BitArray storage;
    private int bitsPerBlock;

    ///////////////////////////////////////////////////////////////////////////

    public BlockContainer(int bitsPerBlock) {
        this.bitsPerBlock = bitsPerBlock;
        this.storage = new BitArray(bitsPerBlock, 4096);
    }

    ///////////////////////////////////////////////////////////////////////////

    public void set(int x, int y, int z, int block) {
        set(getIndex(x, y, z), block);
    }

    protected void set(int index, int block) {
        storage.setAt(index, block);
    }

    ///////////////////////////////////////////////////////////////////////////

    public void write(PacketBuffer buf) {
        buf.writeByte(bitsPerBlock);

        // Global Palette
        if (bitsPerBlock > 8) {
            buf.writeVarIntToBuffer(0);
        }

        buf.writeLongArray(storage.getBackingLongArray());
    }

    ///////////////////////////////////////////////////////////////////////////

    private static int getIndex(int x, int y, int z) {
        return y << 8 | z << 4 | x;
    }

    public int getSerializedSize() {
        return 1 + PacketBuffer.getVarIntSize(0) + PacketBuffer.getVarIntSize(storage.size()) + storage.getBackingLongArray().length * 8;
    }
}
