package com.pipe.netty.handler;

import com.pipe.netty.packet.handshake.C00Handshake;

public interface INetHandlerHandshake {

    void processHandshake(C00Handshake packet);
}
