package com.pipe.netty.packet.status;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerStatus;
import com.pipe.netty.packet.Packet;

public class SPacketPong implements Packet<INetHandlerStatus> {

    private long clientTime;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketPong(long clientTime) {
        this.clientTime = clientTime;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeLong(clientTime);
    }

    @Override
    public void process(INetHandlerStatus handler) {
    }
}
