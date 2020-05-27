package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

import java.util.Map;

public class SPacketStatistics implements Packet<INetHandlerPlay> {

    private Map<String, Integer> statisticMap;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketStatistics(Map<String, Integer> statisticMap) {
        this.statisticMap = statisticMap;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(this.statisticMap.size());

        for (Map.Entry<String, Integer> entry : this.statisticMap.entrySet()) {
            buf.writeString(entry.getKey());
            buf.writeVarIntToBuffer(entry.getValue());
        }
    }
}
