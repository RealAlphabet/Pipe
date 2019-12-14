package com.pipe.netty.handler;

import com.pipe.netty.NetworkManager;
import com.pipe.netty.packet.status.CPacketPing;
import com.pipe.netty.packet.status.CPacketServerQuery;
import com.pipe.netty.packet.status.SPacketPong;
import com.pipe.netty.packet.status.SPacketServerInfo;
import com.pipe.util.ServerStatusResponse;
import com.pipe.util.text.ITextComponent;
import com.pipe.util.text.TextComponentString;

public class NetHandlerStatus implements INetHandlerStatus, INetHandler {

    private NetworkManager networkManager;
    private ServerStatusResponse response;
    private boolean handled;

    ///////////////////////////////////////////////////////////////////////////

    public NetHandlerStatus(NetworkManager networkManager) {
        this.networkManager = networkManager;
        this.response = new ServerStatusResponse();
        this.response.setServerDescription(new TextComponentString("§cSalut les amis"));
        this.response.setPlayers(new ServerStatusResponse.Players(999, 999));
        this.response.setVersion(new ServerStatusResponse.Version("EAGLE", 340));
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onDisconnect(ITextComponent reason) {
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void processServerQuery(CPacketServerQuery packet) {
        if (handled) {
            networkManager.closeChannel();

        } else {
            handled = true;
            networkManager.sendPacket(new SPacketServerInfo(response));
        }
    }

    @Override
    public void processPing(CPacketPing packet) {
        networkManager.sendPacket(new SPacketPong(packet.getClientTime()));
        networkManager.closeChannel();
    }
}
