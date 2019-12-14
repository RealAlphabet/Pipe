package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketKeepAlive implements Packet<INetHandlerPlay> {

    private long key;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        key = buf.readLong();
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleKeepAlive(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public long getKey() {
        return key;
    }
}
