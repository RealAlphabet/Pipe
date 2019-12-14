package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

import java.io.IOException;

public class CPacketCustomPayload implements Packet<INetHandlerPlay> {

    private String channel;
    private PacketBuffer data;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) throws IOException {
        channel = buf.readStringFromBuffer(20);
        int i = buf.readableBytes();

        if (i >= 0 && i <= 32767) {
            data = new PacketBuffer(buf.readBytes(i));

        } else {
            throw new IOException("Payload may not be larger than 32767 bytes");
        }
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleCustomPayload(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public String getChannel() {
        return channel;
    }

    public PacketBuffer getData() {
        return data;
    }
}
