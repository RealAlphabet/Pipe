package com.pipe.netty.packet.status;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerStatus;
import com.pipe.netty.packet.Packet;

public class CPacketPing implements Packet<INetHandlerStatus> {

    private long clientTime;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        this.clientTime = buf.readLong();
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerStatus handler) {
        handler.processPing(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public long getClientTime() {
        return clientTime;
    }
}
