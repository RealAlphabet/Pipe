package com.pipe.netty.handler;

import com.pipe.command.CommandExecutor;
import com.pipe.entity.EntityPlayer;
import com.pipe.entity.Player;
import com.pipe.main.Server;
import com.pipe.netty.NetworkManager;
import com.pipe.netty.packet.Packet;
import com.pipe.netty.packet.play.*;
import com.pipe.netty.packet.play.SPacketAnimation.EnumAnimation;
import com.pipe.netty.packet.play.SPacketEntityStatus.EnumEntityStatus;
import com.pipe.util.EnumHand;
import com.pipe.util.text.ITextComponent;
import com.pipe.world.BlockPos;
import com.pipe.world.EnumDifficulty;
import com.pipe.world.EnumGameType;
import com.pipe.world.EnumWorldType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PlayerConnection implements INetHandlerPlay, INetHandler {

    private NetworkManager networkManager;
    private EntityPlayer entityPlayer;

    ///////////////////////////////////////////////////////////////////////////

    public PlayerConnection(NetworkManager networkManager, EntityPlayer entityPlayer) {
        this.networkManager = networkManager;
        this.entityPlayer = entityPlayer;
        entityPlayer.connection = this;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onDisconnect(ITextComponent reason) {
        Server.getServer().unregisterConnection(entityPlayer);
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void handleAnimation(CPacketAnimation packet) {
        EnumAnimation animation = (packet.getHand() == EnumHand.MAIN_HAND)
                ? EnumAnimation.SWING_MAINHAND
                : EnumAnimation.SWING_OFFHAND;

        for (EntityPlayer entityPlayer : Server.playerList) {
            if (entityPlayer == this.entityPlayer)
                continue;

            entityPlayer.connection.sendPacket(new SPacketAnimation(this.entityPlayer.id, animation));
        }
    }

    @Override   // FINISH
    public void handleChatMessage(CPacketChatMessage packet) {
        String message = packet.getMessage();

        if (CommandExecutor.executeCommand(entityPlayer, message)) {
            return;
        }

        Server.getServer().broadcastMessage(entityPlayer.getName() + " §e» §f" + packet.getMessage());
    }

    @Override
    public void handleTabComplete(CPacketTabComplete packet) {
        List<String> matches = new ArrayList<>();
        String[] words = packet.getMessage().split(" ");

        if (words.length < 2)
            return;

        String word = words[words.length - 1].toLowerCase();

        for (Player player : Server.getServer().getOnlinePlayers())
            if (player.getName().toLowerCase().startsWith(word))
                matches.add(player.getName());

        networkManager.sendPacket(new SPacketTabComplete(matches.toArray(new String[0])));
    }

    @Override
    public void handleClientStatus(CPacketClientStatus packet) {
        switch (packet.getStatus()) {
            case PERFORM_RESPAWN:
                entityPlayer.health = 20.0F;

                networkManager.sendPacket(new SPacketRespawn(0, EnumDifficulty.EASY, EnumGameType.SURVIVAL, EnumWorldType.CUSTOMIZED));
                networkManager.sendPacket(new SPacketPlayerAbilities(EnumGameType.SURVIVAL.getPlayerCapabilities()));

                for (Player player : Server.getServer().getOnlinePlayers()) {
                    if (player == entityPlayer) return;
                    ((EntityPlayer) player).connection.sendPacket(new SPacketSpawnPlayer(entityPlayer.id, entityPlayer.getUUID(), entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, (byte) entityPlayer.yaw, (byte) entityPlayer.pitch));
                }

                entityPlayer.teleport(0.0D, 160.0D, 0.0D, 90.0F, 0.0F);
                break;

            case REQUEST_STATS:
                networkManager.sendPacket(new SPacketStatistics(new HashMap<>()));
                break;
        }
    }

    @Override
    public void handleClientSettings(CPacketClientSettings packet) {

    }

    @Override
    public void handleConfirmTransaction(CPacketConfirmTransaction packet) {

    }

    @Override
    public void handleEnchantItem(CPacketEnchantItem packet) {

    }

    @Override
    public void handleClickWindow(CPacketClickWindow packet) {

    }

    @Override
    public void handlePlaceRecipe(CPacketPlaceRecipe p_194308_1_) {

    }

    @Override
    public void handleCloseWindow(CPacketCloseWindow packet) {

    }

    @Override
    public void handleCustomPayload(CPacketCustomPayload packet) {

    }

    @Override
    public void handleUseEntity(CPacketUseEntity packet) {
        EntityPlayer target = null;

        for (Player player : Server.getServer().getOnlinePlayers())
            if (player.id == packet.getEntityId())
                target = (EntityPlayer) player;

        if (target == null)
            return;

        if (packet.getAction() == CPacketUseEntity.Action.ATTACK) {
            target.health -= 1.0F;
            target.connection.sendPacket(new SPacketEntityVelocity(target.id, 0.0D, 0.3D, 0.0D));
            target.connection.sendPacket(new SPacketUpdateHealth(target.health, 20, 5.0F));
            Server.getServer().broadcastPacket(new SPacketAnimation(target.id, EnumAnimation.TAKE_DAMAGE));

            if (target.health <= 0.0F) {
                target.health = 0.0F;
                Server.getServer().broadcastPacket(new SPacketEntityStatus(target.id, EnumEntityStatus.DIE));
            }
        }
    }

    @Override
    public void handleKeepAlive(CPacketKeepAlive packet) {

    }

    @Override   // FINISH
    public void handlePlayer(CPacketPlayer packet) {
        if (packet.isMoving()) {
            entityPlayer.posX = packet.getX();
            entityPlayer.posY = packet.getY();
            entityPlayer.posZ = packet.getZ();
        }

        if (packet.isRotating()) {
            entityPlayer.yaw = packet.getYaw();
            entityPlayer.pitch = packet.getPitch();
        }

        entityPlayer.onGround = packet.isOnGround();
    }

    @Override
    public void handlePlayerAbilities(CPacketPlayerAbilities packet) {

    }

    @Override
    public void handlePlayerDigging(CPacketPlayerDigging packet) {
        BlockPos blockPos = packet.getPosition();

        for (Player player : Server.getServer().getOnlinePlayers()) {
            if (player == entityPlayer)
                continue;

            double d0 = entityPlayer.posX - player.posX;
            double d1 = entityPlayer.posY - player.posY;
            double d2 = entityPlayer.posZ - player.posZ;

            if (d0 * d0 + d1 * d1 + d2 * d2 < 1024.0D)
                ((EntityPlayer) player).connection.sendPacket(new SPacketBlockBreakAnim(entityPlayer.id, blockPos, (byte) 5));
        }
    }

    @Override   // Process sneak, sprint, wake, sleep animation
    public void handleEntityAction(CPacketEntityAction packet) {
        switch (packet.getAction()) {
            case START_SNEAKING:
                entityPlayer.setSneaking(true);
                break;

            case STOP_SNEAKING:
                entityPlayer.setSneaking(false);
                break;

            case START_SPRINTING:
                entityPlayer.setSprinting(true);
                break;

            case STOP_SPRINTING:
                entityPlayer.setSprinting(false);
                break;

            case STOP_SLEEPING:
                // TODO ACTIONPACKET : START SLEEP
                break;

            case START_RIDING_JUMP:
                // TODO ACTIONPACKET : START RIDING
                break;

            case STOP_RIDING_JUMP:
                // TODO ACTIONPACKET : STOP RIDING
                break;

            case OPEN_INVENTORY:
                // TODO ACTIONPACKET : OPEN INVENTORY
                break;
        }
    }

    @Override
    public void handleInput(CPacketInput packet) {

    }

    @Override
    public void handleHeldItemChange(CPacketHeldItemChange packet) {

    }

    @Override
    public void handleCreativeInventoryAction(CPacketCreativeInventoryAction packet) {

    }

    @Override
    public void handleUpdateSign(CPacketUpdateSign packet) {

    }

    @Override
    public void handleRightClickBlock(CPacketPlayerTryUseItemOnBlock packet) {

    }

    @Override
    public void handlePlayerBlockPlacement(CPacketPlayerTryUseItem packet) {

    }

    @Override
    public void handleSpectate(CPacketSpectate packet) {

    }

    @Override
    public void handleResourcePackStatus(CPacketResourcePackStatus packet) {

    }

    @Override
    public void handleSteerBoat(CPacketSteerBoat packet) {

    }

    @Override
    public void handleVehicleMove(CPacketVehicleMove packet) {

    }

    @Override
    public void handleConfirmTeleport(CPacketConfirmTeleport packet) {
        //  IGNORE THIS PACKET
    }

    @Override
    public void handleRecipeInfo(CPacketRecipeInfo p_191984_1_) {

    }

    @Override
    public void handleSeenAdvancements(CPacketSeenAdvancements p_194027_1_) {

    }

    ///////////////////////////////////////////////////////////////////////////

    public void sendPacket(Packet packet) {
        networkManager.sendPacket(packet);
    }

    public void closeConnection() {
        networkManager.closeChannel();
    }
}
