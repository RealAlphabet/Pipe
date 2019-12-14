package com.pipe.event.login;

import com.pipe.entity.Player;
import com.pipe.event.Event;

public class PlayerJoinEvent extends Event {

    private Player player;
    private String welcomeMessage;

    public PlayerJoinEvent(Player player, String welcomeMessage) {
        this.player = player;
        this.welcomeMessage = welcomeMessage;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  UTILS
    ///////////////////////////////////////////////////////////////////////////

    public void setWelcomeMessage(String message) {
        this.welcomeMessage = welcomeMessage;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  GETTERS
    ///////////////////////////////////////////////////////////////////////////

    public Player getPlayer() {
        return player;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
