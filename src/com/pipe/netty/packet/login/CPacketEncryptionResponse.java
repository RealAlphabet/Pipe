package com.pipe.netty.packet.login;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.NetHandlerLogin;
import com.pipe.netty.packet.Packet;

public class CPacketEncryptionResponse implements Packet<NetHandlerLogin> {

    private byte[] secretKeyEncrypted = new byte[0];
    private byte[] verifyTokenEncrypted = new byte[0];

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        this.secretKeyEncrypted = buf.readByteArray();
        this.verifyTokenEncrypted = buf.readByteArray();
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(NetHandlerLogin handler) {
        handler.processEncryptionResponse(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public byte[] getSecretKeyEncrypted() {
        return secretKeyEncrypted;
    }

    public byte[] getVerifyTokenEncrypted() {
        return verifyTokenEncrypted;
    }
}
