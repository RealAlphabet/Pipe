package com.pipe.netty.packet.play;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.util.text.ITextComponent;
import com.pipe.util.text.TextComponentString;
import com.pipe.world.EnumGameType;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import java.util.List;

public class SPacketPlayerListItem implements Packet<INetHandlerPlay> {

    private Action action;
    private List<PlayerData> players = Lists.newArrayList();

    ///////////////////////////////////////////////////////////////////////////

    public SPacketPlayerListItem(GameProfile gameProfile, Action action) {
        // TODO
        this.action = action;
        this.players.add(new PlayerData(gameProfile, 0, EnumGameType.SURVIVAL, new TextComponentString(gameProfile.getName())));
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeEnumValue(action);
        buf.writeVarIntToBuffer(players.size());

        for (PlayerData playerData : players) {
            switch (this.action) {
                case ADD_PLAYER:
                    buf.writeUuid(playerData.getProfile().getId());
                    buf.writeString(playerData.getProfile().getName());
                    buf.writeVarIntToBuffer(playerData.getProfile().getProperties().size());

                    for (Property property : playerData.getProfile().getProperties().values()) {
                        buf.writeString(property.getName());
                        buf.writeString(property.getValue());

                        if (property.hasSignature()) {
                            buf.writeBoolean(true);
                            buf.writeString(property.getSignature());

                        } else {
                            buf.writeBoolean(false);
                        }
                    }

                    buf.writeVarIntToBuffer(playerData.getGameMode().getId());
                    buf.writeVarIntToBuffer(playerData.getPing());

                    if (playerData.getDisplayName() == null) {
                        buf.writeBoolean(false);

                    } else {
                        buf.writeBoolean(true);
                        buf.writeTextComponent(playerData.getDisplayName());
                    }

                    break;

                case UPDATE_GAME_MODE:
                    buf.writeUuid(playerData.getProfile().getId());
                    buf.writeVarIntToBuffer(playerData.getGameMode().getId());
                    break;

                case UPDATE_LATENCY:
                    buf.writeUuid(playerData.getProfile().getId());
                    buf.writeVarIntToBuffer(playerData.getPing());
                    break;

                case UPDATE_DISPLAY_NAME:
                    buf.writeUuid(playerData.getProfile().getId());

                    if (playerData.getDisplayName() == null) {
                        buf.writeBoolean(false);

                    } else {
                        buf.writeBoolean(true);
                        buf.writeTextComponent(playerData.getDisplayName());
                    }

                    break;

                case REMOVE_PLAYER:
                    buf.writeUuid(playerData.getProfile().getId());
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    public enum Action {

        ADD_PLAYER,
        UPDATE_GAME_MODE,
        UPDATE_LATENCY,
        UPDATE_DISPLAY_NAME,
        REMOVE_PLAYER;
    }

    ///////////////////////////////////////////////////////////////////////////

    public class PlayerData {

        private int ping;
        private EnumGameType gamemode;
        private GameProfile profile;
        private ITextComponent displayName;

        ///////////////////////////////////////////////////////////////////////////

        public PlayerData(GameProfile profileIn, int latency, EnumGameType gameMode, ITextComponent displayName) {
            this.profile = profileIn;
            this.ping = latency;
            this.gamemode = gameMode;
            this.displayName = displayName;
        }

        ///////////////////////////////////////////////////////////////////////////

        public GameProfile getProfile() {
            return this.profile;
        }

        public int getPing() {
            return this.ping;
        }

        public EnumGameType getGameMode() {
            return this.gamemode;
        }

        public ITextComponent getDisplayName() {
            return this.displayName;
        }

        ///////////////////////////////////////////////////////////////////////////

        public String toString() {
            return MoreObjects.toStringHelper(this).add("latency", this.ping).add("gameMode", this.gamemode).add("profile", this.profile).add("displayName", this.displayName == null ? null : ITextComponent.Serializer.componentToJson(this.displayName)).toString();
        }
    }
}
