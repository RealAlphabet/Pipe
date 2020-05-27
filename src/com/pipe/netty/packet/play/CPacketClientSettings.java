package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.util.EnumChatVisibility;
import com.pipe.util.EnumHandSide;

public class CPacketClientSettings implements Packet<INetHandlerPlay> {

    private String lang;
    private int view;
    private EnumChatVisibility chatVisibility;
    private boolean enableColors;
    private int modelPartFlags;
    private EnumHandSide mainHand;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        this.lang = buf.readStringFromBuffer(16);
        this.view = buf.readByte();
        this.chatVisibility = buf.readEnumValue(EnumChatVisibility.class);
        this.enableColors = buf.readBoolean();
        this.modelPartFlags = buf.readUnsignedByte();
        this.mainHand = buf.readEnumValue(EnumHandSide.class);
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleClientSettings(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public String getLang() {
        return lang;
    }

    public int getView() {
        return view;
    }

    public EnumChatVisibility getChatVisibility() {
        return chatVisibility;
    }

    public boolean isEnableColors() {
        return enableColors;
    }

    public int getModelPartFlags() {
        return modelPartFlags;
    }

    public EnumHandSide getMainHand() {
        return mainHand;
    }
}
