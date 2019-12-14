package com.pipe.netty.handler;

import com.pipe.netty.packet.play.*;

public interface INetHandlerPlay {

    void handleAnimation(CPacketAnimation packetIn);

    void handleChatMessage(CPacketChatMessage packetIn);

    void handleTabComplete(CPacketTabComplete packetIn);

    void handleClientStatus(CPacketClientStatus packetIn);

    void handleClientSettings(CPacketClientSettings packetIn);

    void handleConfirmTransaction(CPacketConfirmTransaction packetIn);

    void handleEnchantItem(CPacketEnchantItem packetIn);

    void handleClickWindow(CPacketClickWindow packetIn);

    void handlePlaceRecipe(CPacketPlaceRecipe p_194308_1_);

    void handleCloseWindow(CPacketCloseWindow packetIn);

    void handleCustomPayload(CPacketCustomPayload packetIn);

    void handleUseEntity(CPacketUseEntity packetIn);

    void handleKeepAlive(CPacketKeepAlive packetIn);

    void handlePlayer(CPacketPlayer packetIn);

    void handlePlayerAbilities(CPacketPlayerAbilities packetIn);

    void handlePlayerDigging(CPacketPlayerDigging packetIn);

    void handleEntityAction(CPacketEntityAction packetIn);

    void handleInput(CPacketInput packetIn);

    void handleHeldItemChange(CPacketHeldItemChange packetIn);

    void handleCreativeInventoryAction(CPacketCreativeInventoryAction packetIn);

    void handleUpdateSign(CPacketUpdateSign packetIn);

    void handleRightClickBlock(CPacketPlayerTryUseItemOnBlock packetIn);

    void handlePlayerBlockPlacement(CPacketPlayerTryUseItem packetIn);

    void handleSpectate(CPacketSpectate packetIn);

    void handleResourcePackStatus(CPacketResourcePackStatus packetIn);

    void handleSteerBoat(CPacketSteerBoat packetIn);

    void handleVehicleMove(CPacketVehicleMove packetIn);

    void handleConfirmTeleport(CPacketConfirmTeleport packetIn);

    void handleRecipeInfo(CPacketRecipeInfo p_191984_1_);

    void handleSeenAdvancements(CPacketSeenAdvancements p_194027_1_);
}
