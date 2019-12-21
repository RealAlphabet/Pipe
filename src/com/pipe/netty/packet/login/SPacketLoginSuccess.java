package com.pipe.netty.packet.login;

import com.mojang.authlib.GameProfile;
import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerLogin;
import com.pipe.netty.packet.Packet;

public class SPacketLoginSuccess implements Packet<INetHandlerLogin> {

    private GameProfile profile;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketLoginSuccess(GameProfile profile) {
        this.profile = profile;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeString(profile.getId().toString());
        buf.writeString(profile.getName());
    }

    @Override
    public void process(INetHandlerLogin handler) {
    }
}
