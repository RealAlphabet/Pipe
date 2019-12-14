package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.util.EnumFacing;
import com.pipe.world.BlockPos;

public class CPacketPlayerDigging implements Packet<INetHandlerPlay> {

    private Action action;
    private BlockPos position;
    private EnumFacing facing;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        action = buf.readEnumValue(Action.class);
        position = buf.readBlockPos();
        facing = buf.readEnumValue(EnumFacing.class);
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handlePlayerDigging(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public BlockPos getPosition() {
        return position;
    }

    public Action getAction() {
        return action;
    }

    public EnumFacing getFacing() {
        return facing;
    }

    ///////////////////////////////////////////////////////////////////////////

    public enum Action {

        START_DESTROY_BLOCK,
        ABORT_DESTROY_BLOCK,
        STOP_DESTROY_BLOCK,
        DROP_ALL_ITEMS,
        DROP_ITEM,
        RELEASE_USE_ITEM,
        SWAP_HELD_ITEMS;
    }
}
