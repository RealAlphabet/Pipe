package com.pipe.netty.packet.handshake;

import com.pipe.netty.EnumConnectionState;
import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerHandshake;
import com.pipe.netty.packet.Packet;

public class C00Handshake implements Packet<INetHandlerHandshake> {

    private int protocolVersion;
    private String ip;
    private int port;
    private EnumConnectionState requestedState;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        this.protocolVersion = buf.readVarIntFromBuffer();
        this.ip = buf.readStringFromBuffer(255);
        this.port = buf.readUnsignedShort();
        this.requestedState = EnumConnectionState.getById(buf.readVarIntFromBuffer());
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerHandshake handler) {
        handler.processHandshake(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public EnumConnectionState getRequestedState() {
        return requestedState;
    }

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }
}
