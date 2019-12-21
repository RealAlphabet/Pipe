package com.pipe.netty;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.pipe.netty.packet.Packet;
import com.pipe.netty.packet.SPacketDisconnect;
import com.pipe.netty.packet.handshake.C00Handshake;
import com.pipe.netty.packet.login.*;
import com.pipe.netty.packet.play.*;
import com.pipe.netty.packet.status.CPacketPing;
import com.pipe.netty.packet.status.CPacketServerQuery;
import com.pipe.netty.packet.status.SPacketPong;
import com.pipe.netty.packet.status.SPacketServerInfo;

import java.util.Map;

public enum EnumConnectionState {

    HANDSHAKING(-1) {
        {
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C00Handshake.class);
        }
    },
    PLAY(0) {
        {
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnObject.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnExperienceOrb.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnGlobalEntity.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnMob.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnPainting.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnPlayer.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketAnimation.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketStatistics.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketBlockBreakAnim.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUpdateTileEntity.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketBlockAction.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketBlockChange.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUpdateBossInfo.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketServerDifficulty.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketTabComplete.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketChat.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketMultiBlockChange.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketConfirmTransaction.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCloseWindow.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketOpenWindow.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketWindowItems.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketWindowProperty.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSetSlot.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCooldown.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCustomPayload.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCustomSound.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketDisconnect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityStatus.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketExplosion.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUnloadChunk.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketChangeGameState.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketKeepAlive.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketChunkData.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEffect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketParticles.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketJoinGame.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketMaps.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntity.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntity.SPacketEntityRelMove.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntity.SPacketEntityLookMove.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntity.SPacketEntityLook.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketMoveVehicle.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSignEditorOpen.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPlaceGhostRecipe.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPlayerAbilities.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCombatEvent.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPlayerListItem.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPlayerPosLook.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUseBed.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketRecipeBook.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketDestroyEntities.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketRemoveEntityEffect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketResourcePackSend.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketRespawn.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityHeadLook.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSelectAdvancementsTab.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketWorldBorder.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCamera.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketHeldItemChange.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketDisplayObjective.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityMetadata.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityAttach.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityVelocity.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityEquipment.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSetExperience.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUpdateHealth.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketScoreboardObjective.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSetPassengers.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketTeams.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketUpdateScore.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSpawnPosition.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketTimeUpdate.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketTitle.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketSoundEffect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPlayerListHeaderFooter.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketCollectItem.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityTeleport.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketAdvancementInfo.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityProperties.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEntityEffect.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketConfirmTeleport.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketTabComplete.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketChatMessage.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketClientStatus.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketClientSettings.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketConfirmTransaction.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketEnchantItem.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketClickWindow.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketCloseWindow.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketCustomPayload.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketUseEntity.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketKeepAlive.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayer.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayer.Position.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayer.PositionRotation.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayer.Rotation.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketVehicleMove.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketSteerBoat.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlaceRecipe.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayerAbilities.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayerDigging.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketEntityAction.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketInput.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketRecipeInfo.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketResourcePackStatus.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketSeenAdvancements.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketHeldItemChange.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketCreativeInventoryAction.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketUpdateSign.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketAnimation.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketSpectate.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayerTryUseItemOnBlock.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPlayerTryUseItem.class);
        }
    },
    STATUS(1) {
        {
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketServerQuery.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketServerInfo.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketPing.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketPong.class);
        }
    },
    LOGIN(2) {
        {
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketDisconnect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEncryptionRequest.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketLoginSuccess.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, SPacketEnableCompression.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketLoginStart.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, CPacketEncryptionResponse.class);
        }
    };

    ///////////////////////////////////////////////////////////////////////////

    private static final EnumConnectionState[] STATES_BY_ID = new EnumConnectionState[4];

    static {
        for (EnumConnectionState enumconnectionstate : values()) {
            STATES_BY_ID[enumconnectionstate.getMode() + 1] = enumconnectionstate;
        }
    }

    public static EnumConnectionState getById(int state) {
        return STATES_BY_ID[state + 1];
    }

    ///////////////////////////////////////////////////////////////////////////

    private Map<EnumPacketDirection, BiMap<Integer, Class<? extends Packet>>> directionMaps;
    private int mode;

    ///////////////////////////////////////////////////////////////////////////

    EnumConnectionState(int mode) {
        this.directionMaps = Maps.newHashMap();
        this.directionMaps.put(EnumPacketDirection.CLIENTBOUND, HashBiMap.create());
        this.directionMaps.put(EnumPacketDirection.SERVERBOUND, HashBiMap.create());
        this.mode = mode;
    }

    protected void registerPacket(EnumPacketDirection direction, Class<? extends Packet> packetClass) {
        BiMap<Integer, Class<? extends Packet>> bimap = this.directionMaps.get(direction);
        bimap.put(bimap.size(), packetClass);
    }

    ///////////////////////////////////////////////////////////////////////////

    public Packet getPacket(EnumPacketDirection direction, int packetId) throws IllegalAccessException, InstantiationException {
        Class packetClass = (Class) (((BiMap) directionMaps.get(direction)).get(packetId));
        return packetClass == null ? null : (Packet) packetClass.newInstance();
    }

    public Integer getPacketId(EnumPacketDirection direction, Packet packet) {
        return (Integer) (((BiMap) directionMaps.get(direction)).inverse().get(packet.getClass()));
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getMode() {
        return mode;
    }
}