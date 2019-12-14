package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketClickWindow implements Packet<INetHandlerPlay> {

    private int windowId;
    private int slotId;
    private int usedButton;
    private short actionNumber;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {

    }

    @Override
    public void write(PacketBuffer buf) {

    }

    @Override
    public void process(INetHandlerPlay handler) {

    }
}
