package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketCustomPayload implements Packet<INetHandlerPlay> {

    private String channel;
    private PacketBuffer data;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketCustomPayload(String channel, PacketBuffer data) {
        this.channel = channel;
        this.data = data;

        if (data.writerIndex() > 1048576)
            throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeString(this.channel);
        buf.writeBytes(this.data);
    }
}
