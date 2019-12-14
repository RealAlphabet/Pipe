package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketEntityAction implements Packet<INetHandlerPlay> {

    private int entityId;
    private Action action;
    private int auxData;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        entityId = buf.readVarIntFromBuffer();
        action = buf.readEnumValue(Action.class);
        auxData = buf.readVarIntFromBuffer();
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleEntityAction(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getEntityId() {
        return entityId;
    }

    public Action getAction() {
        return action;
    }

    public int getAuxData() {
        return auxData;
    }

    ///////////////////////////////////////////////////////////////////////////

    public enum Action {

        START_SNEAKING,
        STOP_SNEAKING,
        STOP_SLEEPING,
        START_SPRINTING,
        STOP_SPRINTING,
        START_RIDING_JUMP,
        STOP_RIDING_JUMP,
        OPEN_INVENTORY,
        START_FALL_FLYING
    }
}
