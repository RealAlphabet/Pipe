package com.pipe.netty.handler;

import com.pipe.entity.EntityPlayer;
import com.pipe.main.Server;
import com.pipe.netty.EnumConnectionState;
import com.pipe.netty.NetworkManager;
import com.pipe.netty.packet.login.CPacketEncryptionResponse;
import com.pipe.netty.packet.login.CPacketLoginStart;
import com.pipe.netty.packet.login.SPacketLoginSuccess;
import com.pipe.util.text.ITextComponent;
import com.pipe.util.text.TextComponentString;
import com.pipe.world.BlockPos;
import com.pipe.world.EnumDifficulty;
import com.pipe.world.EnumGameType;
import com.pipe.world.EnumWorldType;
import com.pipe.world.chunk.Chunk;
import com.mojang.authlib.GameProfile;
import com.pipe.netty.packet.play.*;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.UUID;

public class NetHandlerLogin implements INetHandlerLogin, INetHandler {

    private NetworkManager networkManager;

    ///////////////////////////////////////////////////////////////////////////

    public NetHandlerLogin(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onDisconnect(ITextComponent reason) {
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void processLoginStart(CPacketLoginStart packet) {
        GameProfile gameProfile = new GameProfile(UUID.nameUUIDFromBytes(("OfflinePlayer:" + packet.getUsername()).getBytes(StandardCharsets.UTF_8)), packet.getUsername());
        EntityPlayer player = new EntityPlayer(gameProfile);

        System.out.println(gameProfile.getName() + " s'est connecté !");

        networkManager.sendPacket(new SPacketLoginSuccess(gameProfile));
        networkManager.setConnectionState(EnumConnectionState.PLAY);
        networkManager.setNetHandler(new PlayerConnection(this.networkManager, player));

        networkManager.sendPacket(new SPacketJoinGame(player.id, EnumGameType.SURVIVAL, false, 0, EnumDifficulty.EASY, 300, EnumWorldType.DEFAULT, false));
        networkManager.sendPacket(new SPacketPlayerAbilities());
        networkManager.sendPacket(new SPacketPlayerListItem(gameProfile, SPacketPlayerListItem.Action.ADD_PLAYER));
        networkManager.sendPacket(new SPacketPlayerPosLook(0, 160, 0, 90.0F, 0.0F, Collections.emptySet(), 0));
        networkManager.sendPacket(new SPacketSpawnPosition(new BlockPos(0, 160, 0)));
        networkManager.sendPacket(new SPacketChat(new TextComponentString("§f \n Bonjour §b" + packet.getUsername() + "§r, tu es connecté sur un serveur utilisant §ePipe §run serveur Minecraft léger et rapide développé par §eiRandomXx.\n §r")));

        Server.getServer().registerConnection(player);

        for (int x = -3; x < 3; x++) {
            for (int z = -3; z < 3; z++) {
                networkManager.sendPacket(new SPacketChunkData(new Chunk(x, z)));
            }
        }
    }

    @Override
    public void processEncryptionResponse(CPacketEncryptionResponse packet) {
        // TODO
    }
}
