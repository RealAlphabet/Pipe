package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketClientStatus implements Packet<INetHandlerPlay> {

    private State status;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        status = buf.readEnumValue(State.class);
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleClientStatus(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public State getStatus() {
        return status;
    }

    ///////////////////////////////////////////////////////////////////////////

    public static enum State {

        PERFORM_RESPAWN,
        REQUEST_STATS;
    }
}
