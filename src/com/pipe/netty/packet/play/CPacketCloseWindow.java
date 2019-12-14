package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketCloseWindow implements Packet<INetHandlerPlay> {

    private int windowId;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        windowId = buf.readByte();
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleCloseWindow(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getWindowId() {
        return windowId;
    }
}
