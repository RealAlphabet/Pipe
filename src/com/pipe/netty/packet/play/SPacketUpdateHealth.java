package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketUpdateHealth implements Packet<INetHandlerPlay> {

    private float health;
    private int food;
    private float foodSaturation;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketUpdateHealth(float health, int food, float foodSaturation) {
        this.health = health;
        this.food = food;
        this.foodSaturation = foodSaturation;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeFloat(health);
        buf.writeVarIntToBuffer(food);
        buf.writeFloat(foodSaturation);
    }
}
