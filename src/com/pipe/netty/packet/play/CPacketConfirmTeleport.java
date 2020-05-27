package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketConfirmTeleport implements Packet<INetHandlerPlay> {

    private int teleportId;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        teleportId = buf.readVarIntFromBuffer();
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleConfirmTeleport(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getTeleportId() {
        return teleportId;
    }
}
