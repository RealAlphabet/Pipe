package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketInput implements Packet<INetHandlerPlay> {

    private float strafeSpeed;
    private float forwardSpeedIn;
    private boolean jumping;
    private boolean sneaking;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        strafeSpeed = buf.readFloat();
        forwardSpeedIn = buf.readFloat();

        byte mask = buf.readByte();
        jumping = (mask & 1) > 0;
        sneaking = (mask & 2) > 0;
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleInput(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public float getStrafeSpeed() {
        return strafeSpeed;
    }

    public float getForwardSpeedIn() {
        return forwardSpeedIn;
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isSneaking() {
        return sneaking;
    }
}
