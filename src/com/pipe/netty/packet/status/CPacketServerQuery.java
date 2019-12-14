package com.pipe.netty.packet.status;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerStatus;
import com.pipe.netty.packet.Packet;

public class CPacketServerQuery implements Packet<INetHandlerStatus> {

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerStatus handler) {
        handler.processServerQuery(this);
    }
}
