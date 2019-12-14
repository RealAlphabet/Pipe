package com.pipe.netty.handler;

import com.pipe.netty.EnumConnectionState;
import com.pipe.netty.NetworkManager;
import com.pipe.netty.packet.handshake.C00Handshake;
import com.pipe.util.text.ITextComponent;

public class NetHandlerHandshake implements INetHandlerHandshake, INetHandler {

    private NetworkManager networkManager;

    ///////////////////////////////////////////////////////////////////////////

    public NetHandlerHandshake(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onDisconnect(ITextComponent reason) {
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void processHandshake(C00Handshake packet) {
        switch (packet.getRequestedState()) {
            case LOGIN:
                networkManager.setConnectionState(EnumConnectionState.LOGIN);
                networkManager.setNetHandler(new NetHandlerLogin(networkManager));
                break;

            case STATUS:
                networkManager.setConnectionState(EnumConnectionState.STATUS);
                networkManager.setNetHandler(new NetHandlerStatus(networkManager));
                break;

            default:
                throw new UnsupportedOperationException("Invalid intention " + packet.getRequestedState());
        }
    }
}
