package com.pipe.netty.packet.status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerStatus;
import com.pipe.netty.packet.Packet;
import com.pipe.util.EnumTypeAdapterFactory;
import com.pipe.util.ServerStatusResponse;
import com.pipe.util.text.ITextComponent;
import com.pipe.util.text.Style;

public class SPacketServerInfo implements Packet<INetHandlerStatus> {

    private static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(ServerStatusResponse.Version.class, new ServerStatusResponse.Version.Serializer()).registerTypeAdapter(ServerStatusResponse.Players.class, new ServerStatusResponse.Players.Serializer()).registerTypeAdapter(ServerStatusResponse.class, new ServerStatusResponse.Serializer()).registerTypeHierarchyAdapter(ITextComponent.class, new ITextComponent.Serializer()).registerTypeHierarchyAdapter(Style.class, new Style.Serializer()).registerTypeAdapterFactory(new EnumTypeAdapterFactory()).create();
    private ServerStatusResponse response;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketServerInfo(ServerStatusResponse response) {
        this.response = response;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeString(GSON.toJson(response));
    }

    @Override
    public void process(INetHandlerStatus handler) {
    }
}
