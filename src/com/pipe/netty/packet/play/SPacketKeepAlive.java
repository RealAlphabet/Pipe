package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketKeepAlive implements Packet<INetHandlerPlay> {

    private long id;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketKeepAlive(long id) {
        this.id = id;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeLong(this.id);
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }
}
