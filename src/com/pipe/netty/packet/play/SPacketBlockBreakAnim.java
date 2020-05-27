package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.world.BlockPos;

public class SPacketBlockBreakAnim implements Packet<INetHandlerPlay> {

    private int entityId;
    private BlockPos blockPos;
    private byte destroyState;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketBlockBreakAnim(int entityId, BlockPos blockPos, byte destroyState) {
        this.entityId = entityId;
        this.blockPos = blockPos;
        this.destroyState = destroyState;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(entityId);
        buf.writeBlockPos(blockPos);
        buf.writeByte(destroyState);
    }
}
