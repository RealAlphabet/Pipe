package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.world.EnumDifficulty;
import com.pipe.world.EnumGameType;
import com.pipe.world.EnumWorldType;

public class SPacketRespawn implements Packet<INetHandlerPlay> {

    private int dimensionID;
    private EnumDifficulty difficulty;
    private EnumGameType gameType;
    private EnumWorldType worldType;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketRespawn(int dimensionID, EnumDifficulty difficulty, EnumGameType gameType, EnumWorldType worldType) {
        this.dimensionID = dimensionID;
        this.difficulty = difficulty;
        this.gameType = gameType;
        this.worldType = worldType;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeInt(this.dimensionID);
        buf.writeByte(this.difficulty.getId());
        buf.writeByte(this.gameType.getId());
        buf.writeString(this.worldType.getName());
    }
}
