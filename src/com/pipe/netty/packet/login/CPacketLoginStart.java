package com.pipe.netty.packet.login;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.NetHandlerLogin;
import com.pipe.netty.packet.Packet;

public class CPacketLoginStart implements Packet<NetHandlerLogin> {

    private String username;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        this.username = buf.readStringFromBuffer(16);
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(NetHandlerLogin handler) {
        handler.processLoginStart(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public String getUsername() {
        return username;
    }
}
