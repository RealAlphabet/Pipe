package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.world.EnumDifficulty;
import com.pipe.world.EnumGameType;
import com.pipe.world.EnumWorldType;

public class SPacketJoinGame implements Packet<INetHandlerPlay> {

    private int playerId;
    private boolean hardcore;
    private EnumGameType gameType;
    private int dimension;
    private EnumDifficulty difficulty;
    private int maxPlayers;
    private EnumWorldType worldType;
    private boolean reducedDebugInfo;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketJoinGame(int playerId, EnumGameType gameType, boolean hardcore, int dimension, EnumDifficulty difficulty, int maxPlayers, EnumWorldType worldType, boolean reducedDebugInfo) {
        this.playerId = playerId;
        this.gameType = gameType;
        this.hardcore = hardcore;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.worldType = worldType;
        this.reducedDebugInfo = reducedDebugInfo;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeInt(playerId);
        int i = gameType.getId();

        if (hardcore)
            i |= 8;

        buf.writeByte(i);
        buf.writeInt(dimension);
        buf.writeByte(difficulty.getId());
        buf.writeByte(maxPlayers);
        buf.writeString(worldType.getName());
        buf.writeBoolean(reducedDebugInfo);
    }
}
