package com.pipe.entity;

import com.mojang.authlib.GameProfile;
import com.pipe.util.text.ChatType;
import com.pipe.util.text.ITextComponent;
import com.pipe.world.EnumGameType;

import java.util.UUID;

public abstract class Player extends Entity {

    ///////////////////////////////////////////////////////////////////////////
    //  MESSAGE
    ///////////////////////////////////////////////////////////////////////////

    public abstract void sendMessage(String message);

    public abstract void sendMessage(String message, ChatType type);

    public abstract void sendMessage(ITextComponent message);

    public abstract void sendMessage(ITextComponent message, ChatType type);


    ///////////////////////////////////////////////////////////////////////////
    //  KICK
    ///////////////////////////////////////////////////////////////////////////

    public abstract void kick(String reason);

    public abstract void kick(ITextComponent reason);
    
    ///////////////////////////////////////////////////////////////////////////
	//  GAMEMODE
	///////////////////////////////////////////////////////////////////////////

    public abstract void setGameMode(EnumGameType type);

    ///////////////////////////////////////////////////////////////////////////
    //  GETTERS
    ///////////////////////////////////////////////////////////////////////////

    public abstract GameProfile getProfile();

    public abstract String getName();

    public abstract UUID getUUID();
    
    public abstract EnumGameType getGameMode();
}
