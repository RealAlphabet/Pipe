package com.pipe.event.login;

import com.pipe.event.Event;

import java.net.InetSocketAddress;

public class PlayerLoginEvent extends Event {

    private String disallowMessage;
    private boolean disallow;

    private InetSocketAddress userAddress;
    private String username;
    private String hostIp;
    private String hostPort;
    private int protocolVersion;

    public PlayerLoginEvent(InetSocketAddress userAddress, String username, String hostIp, String hostPort, int protocolVersion) {
        this.userAddress = userAddress;
        this.username = username;
        this.hostIp = hostIp;
        this.hostPort = hostPort;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  UTILS
    ///////////////////////////////////////////////////////////////////////////

    public void setDenyMessage(String message) {
        this.disallowMessage = message;
    }

    public void disallow() {
        this.disallow = true;
    }

    public void allow() {
        this.disallow = false;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  GETTERS
    ///////////////////////////////////////////////////////////////////////////

    public String getDisallowMessage() {
        return disallowMessage;
    }

    public boolean isDisallowed() {
        return disallow;
    }

    public InetSocketAddress getAddress() {
        return userAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getHostIp() {
        return hostIp;
    }

    public String  getHostPort() {
        return hostPort;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }
}