package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketChangeGameState implements Packet<INetHandlerPlay> {

    private int state;
    private float value;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketChangeGameState(int state, float value) {
        this.state = state;
        this.value = value;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeByte(state);
        buf.writeFloat(value);
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }
}
