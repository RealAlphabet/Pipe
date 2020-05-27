package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketTabComplete implements Packet<INetHandlerPlay> {

    private String[] matches;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketTabComplete(String[] matches) {
        this.matches = matches;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(matches.length);

        for (String match : matches)
            buf.writeString(match);
    }
}
