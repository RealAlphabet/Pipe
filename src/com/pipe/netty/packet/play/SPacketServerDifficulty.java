package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.world.EnumDifficulty;

public class SPacketServerDifficulty implements Packet<INetHandlerPlay> {

    private EnumDifficulty difficulty;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketServerDifficulty(EnumDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeByte(this.difficulty.getId());
    }
}
