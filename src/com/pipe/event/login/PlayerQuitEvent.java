package com.pipe.event.login;

import com.pipe.entity.Player;
import com.pipe.event.Event;

public class PlayerQuitEvent extends Event {

    private Player player;
    private String quitMessage;

    public PlayerQuitEvent(Player player, String quitMessage) {
        this.player = player;
        this.quitMessage = quitMessage;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  UTILS
    ///////////////////////////////////////////////////////////////////////////

    public void setWelcomeMessage(String message) {
        this.quitMessage = quitMessage;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  GETTERS
    ///////////////////////////////////////////////////////////////////////////

    public Player getPlayer() {
        return player;
    }

    public String getQuitMessage() {
        return quitMessage;
    }
}
