package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketConfirmTransaction implements Packet<INetHandlerPlay> {

    private int windowId;
    private short uid;
    private boolean accepted;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        windowId = buf.readByte();
        uid = buf.readShort();
        accepted = buf.readBoolean();
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleConfirmTransaction(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getWindowId() {
        return windowId;
    }

    public short getUid() {
        return uid;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
