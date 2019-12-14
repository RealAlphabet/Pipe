package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketPlayerAbilities implements Packet<INetHandlerPlay> {

    private boolean invulnerable;
    private boolean flying;
    private boolean allowFlying;
    private boolean creativeMode;
    private float flySpeed;
    private float walkSpeed;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        byte mask = buf.readByte();
        invulnerable = (mask & 1) > 0;
        flying = (mask & 2) > 0;
        allowFlying = (mask & 4) > 0;
        creativeMode = (mask & 8) > 0;
        flySpeed = buf.readFloat();
        walkSpeed = buf.readFloat();
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handlePlayerAbilities(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public boolean isInvulnerable() {
        return invulnerable;
    }

    public boolean isFlying() {
        return flying;
    }

    public boolean isAllowFlying() {
        return allowFlying;
    }

    public boolean isCreativeMode() {
        return creativeMode;
    }

    public float getFlySpeed() {
        return flySpeed;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }
}
