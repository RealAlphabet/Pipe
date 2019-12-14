package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

import java.util.UUID;

public class SPacketSpawnPlayer implements Packet<INetHandlerPlay> {

    private int entityId;
    private UUID uniqueId;
    private double x;
    private double y;
    private double z;
    private byte yaw;
    private byte pitch;
//    private EntityDataManager watcher; TODO

    ///////////////////////////////////////////////////////////////////////////

    // TODO REPLACE WITH ENTITY PLAYER
    public SPacketSpawnPlayer(int entityId, UUID uniqueId, double x, double y, double z, byte yaw, byte pitch) {
        this.entityId = entityId;
        this.uniqueId = uniqueId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(entityId);
        buf.writeUuid(uniqueId);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeByte(yaw);
        buf.writeByte(pitch);
//        watcher.writeEntries(buf);
        buf.writeByte(0xFF); //TODO
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }
}
