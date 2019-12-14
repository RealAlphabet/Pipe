package com.pipe.netty.handler;

import com.pipe.netty.packet.status.CPacketPing;
import com.pipe.netty.packet.status.CPacketServerQuery;

public interface INetHandlerStatus {

    void processServerQuery(CPacketServerQuery packet);

    void processPing(CPacketPing packet);
}
