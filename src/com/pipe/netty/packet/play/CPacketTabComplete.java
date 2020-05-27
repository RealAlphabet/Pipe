package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.world.BlockPos;

public class CPacketTabComplete implements Packet<INetHandlerPlay> {

    private String message;
    private boolean hasTargetBlock;
    private BlockPos targetBlock;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        this.message = buf.readStringFromBuffer(32767);
        this.hasTargetBlock = buf.readBoolean();

        if (buf.readBoolean())
            this.targetBlock = buf.readBlockPos();
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleTabComplete(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public String getMessage() {
        return message;
    }

    public BlockPos getTargetBlock() {
        return targetBlock;
    }

    public boolean hasTargetBlock() {
        return hasTargetBlock;
    }
}
