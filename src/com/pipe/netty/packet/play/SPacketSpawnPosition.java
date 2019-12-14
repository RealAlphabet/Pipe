package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.world.BlockPos;

public class SPacketSpawnPosition implements Packet<INetHandlerPlay> {

    public BlockPos spawnBlockPos;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketSpawnPosition(BlockPos spawnBlockPos) {
        this.spawnBlockPos = spawnBlockPos;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeBlockPos(this.spawnBlockPos);
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }
}
