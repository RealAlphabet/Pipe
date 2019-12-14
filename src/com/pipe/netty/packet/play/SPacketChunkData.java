package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.world.chunk.Chunk;
import com.pipe.world.chunk.ChunkSection;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class SPacketChunkData implements Packet<INetHandlerPlay> {

    private int chunkX;
    private int chunkZ;
    private boolean fullChunk;
    private byte[] buffer;
    private int availableSections;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketChunkData(Chunk chunk) {
        this.chunkX = chunk.getXPos();
        this.chunkZ = chunk.getZPos();
        this.fullChunk = true;
        this.buffer = new byte[calculateSectionsSize(chunk, true)];

        ByteBuf bytebuf = Unpooled.wrappedBuffer(buffer);
        bytebuf.writerIndex(0);

        this.availableSections = writeSections(new PacketBuffer(bytebuf), chunk, true);
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeInt(chunkX);
        buf.writeInt(chunkZ);
        buf.writeBoolean(fullChunk);

        // Sections
        buf.writeVarIntToBuffer(availableSections);
        buf.writeVarIntToBuffer(buffer.length);
        buf.writeBytes(buffer);

        // Entities
        writeTileEntities(buf);
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }

    ///////////////////////////////////////////////////////////////////////////

    public int writeSections(PacketBuffer buf, Chunk chunk, boolean hasSky) {
        ChunkSection[] chunkSections = chunk.getSectionsArray();
        int i = 0;
        int j = 0;

        // Data
        for (int k = chunkSections.length; j < k; ++j) {
            ChunkSection chunkSection = chunkSections[j];

            if (chunkSection == null) {
                continue;
            }

            i |= 1 << j;
            chunkSection.getData().write(buf);
            buf.writeBytes(chunkSection.getBlocklightArray().getData());

            if (hasSky) {
                buf.writeBytes(chunkSection.getSkylightArray().getData());
            }
        }

        // Biomes
        if (fullChunk) {
            buf.writeBytes(chunk.getBiomeArray());
        }

        return i;
    }

    protected int calculateSectionsSize(Chunk chunk, boolean hasSky) {
        ChunkSection[] chunkSections = chunk.getSectionsArray();
        int i = 0;
        int j = 0;

        // Data
        for (int k = chunkSections.length; j < k; ++j) {
            ChunkSection chunkSection = chunkSections[j];

            if (chunkSection == null) {
                continue;
            }

            i = i + chunkSection.getData().getSerializedSize();
            i = i + chunkSection.getBlocklightArray().getData().length;

            if (hasSky) {
                i += chunkSection.getSkylightArray().getData().length;
            }
        }

        // Biomes
        if (fullChunk) {
            i += chunk.getBiomeArray().length;
        }

        return i;
    }

    private void writeTileEntities(PacketBuffer buf) {
        buf.writeVarIntToBuffer(0); // Number of block entities
    }
}
