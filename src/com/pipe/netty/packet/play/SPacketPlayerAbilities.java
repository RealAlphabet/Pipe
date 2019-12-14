package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketPlayerAbilities implements Packet<INetHandlerPlay> {

    private boolean invulnerable;
    private boolean flying;
    private boolean allowFlying;
    private boolean creativeMode;
    private float flySpeed;
    private float walkSpeed;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketPlayerAbilities() {
        // TODO
        flying = true;
        allowFlying = true;
        flySpeed = 0.1F;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        byte b0 = 0;

        if (this.invulnerable) {
            b0 = (byte) (b0 | 1);
        }

        if (this.flying) {
            b0 = (byte) (b0 | 2);
        }

        if (this.allowFlying) {
            b0 = (byte) (b0 | 4);
        }

        if (this.creativeMode) {
            b0 = (byte) (b0 | 8);
        }

        buf.writeByte(b0);
        buf.writeFloat(this.flySpeed);
        buf.writeFloat(this.walkSpeed);
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }
}
