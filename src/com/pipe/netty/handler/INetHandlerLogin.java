package com.pipe.netty.handler;

import com.pipe.netty.packet.login.CPacketEncryptionResponse;
import com.pipe.netty.packet.login.CPacketLoginStart;

public interface INetHandlerLogin {

    void processLoginStart(CPacketLoginStart packet);

    void processEncryptionResponse(CPacketEncryptionResponse packet);
}
