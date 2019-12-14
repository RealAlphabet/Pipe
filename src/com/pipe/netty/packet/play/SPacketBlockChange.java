package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.world.BlockPos;

public class SPacketBlockChange implements Packet<INetHandlerPlay> {

    private BlockPos blockPos;
    private int blockState;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketBlockChange(BlockPos blockPos, int blockState) {
        this.blockPos = blockPos;
        this.blockState = blockState;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeBlockPos(blockPos);
        buf.writeVarIntToBuffer(blockState);
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }
}
