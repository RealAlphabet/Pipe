package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.util.text.ChatType;
import com.pipe.util.text.ITextComponent;

public class SPacketChat implements Packet<INetHandlerPlay> {

    private ITextComponent chatComponent;
    private ChatType type;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketChat(ITextComponent component) {
        this(component, ChatType.SYSTEM);
    }

    public SPacketChat(ITextComponent component, ChatType type) {
        this.chatComponent = component;
        this.type = type;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeTextComponent(chatComponent);
        buf.writeByte(type.getId());
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }
}
