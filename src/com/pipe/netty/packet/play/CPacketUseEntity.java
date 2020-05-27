package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.util.EnumHand;
import com.pipe.util.math.Vec3d;

public class CPacketUseEntity implements Packet<INetHandlerPlay> {

    private int entityId;
    private Action action;
    private Vec3d hitVec;
    private EnumHand hand;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        this.entityId = buf.readVarIntFromBuffer();
        this.action = buf.readEnumValue(Action.class);

        if (this.action == Action.INTERACT_AT)
            this.hitVec = new Vec3d(buf.readFloat(), buf.readFloat(), buf.readFloat());

        if (this.action == Action.INTERACT || this.action == Action.INTERACT_AT)
            this.hand = buf.readEnumValue(EnumHand.class);
    }

    @Override
    public void process(INetHandlerPlay handler) {
        if (this.action == Action.INTERACT)
            return;

        if (this.action == Action.INTERACT_AT && this.hand == EnumHand.OFF_HAND)
            return;

        handler.handleUseEntity(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getEntityId() {
        return entityId;
    }

    public Action getAction() {
        return action;
    }

    public Vec3d getHitVec() {
        return hitVec;
    }

    public EnumHand getHand() {
        return hand;
    }

    ///////////////////////////////////////////////////////////////////////////

    public static enum Action {
        INTERACT,
        ATTACK,
        INTERACT_AT;
    }
}
