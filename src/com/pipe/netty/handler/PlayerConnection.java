package com.pipe.netty.handler;

import com.pipe.command.CommandExecutor;
import com.pipe.entity.EntityPlayer;
import com.pipe.main.Server;
import com.pipe.netty.NetworkManager;
import com.pipe.netty.packet.Packet;
import com.pipe.netty.packet.play.*;
import com.pipe.netty.packet.play.SPacketAnimation.EnumAnimation;
import com.pipe.util.EnumHand;
import com.pipe.util.text.ITextComponent;
import com.pipe.world.BlockPos;

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

    @Override   // FINISH
    public void handleAnimation(CPacketAnimation packet) {
        EnumAnimation animation = (packet.getHand() == EnumHand.MAIN_HAND)
                ? EnumAnimation.SWING_MAINHAND
                : EnumAnimation.SWING_OFFHAND;

        for (EntityPlayer entityPlayer : Server.playerList) {
            if (entityPlayer == this.entityPlayer) {
                continue;
            }

            entityPlayer.connection.sendPacket(new SPacketAnimation(this.entityPlayer, animation));
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
        System.out.println("TAB : " + packet.getMessage());
    }

    @Override
    public void handleClientStatus(CPacketClientStatus packet) {

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
        CPacketPlayerDigging.Action action = packet.getAction();
        BlockPos blockPos = packet.getPosition();

        // Handle digging
        System.out.println(blockPos.getX() + " " + blockPos.getY() + " " + blockPos.getZ());
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

            default:
                entityPlayer.kick("§cInvalid entity action, modified client ?");
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
