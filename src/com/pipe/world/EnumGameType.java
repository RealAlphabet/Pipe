package com.pipe.world;

import com.pipe.entity.PlayerCapabilities;

public enum EnumGameType {

    NOT_SET(-1, new PlayerCapabilities(0.1F,0F,false,false,false), "" , ""),
    SURVIVAL(0, new PlayerCapabilities(0.1F,0.1F,false,false,false), "s", "survival"),
    CREATIVE(1, new PlayerCapabilities(0.1F,0.1F,true,true,true), "c", "creative"),
    ADVENTURE(2, new PlayerCapabilities(0.1F,0.1F,false,false,false), "a", "adventure"),
    SPECTATOR(3, new PlayerCapabilities(0.1F,0.1F,true,true,false), "spec", "spectator");

    ///////////////////////////////////////////////////////////////////////////

    private int id;
    private PlayerCapabilities pc;
    private String shortname, fullname;

    ///////////////////////////////////////////////////////////////////////////

    EnumGameType(int id, PlayerCapabilities pc, String shortname, String fullname) {
        this.id = id;
        this.pc = pc;
        this.fullname = fullname;
        this.shortname = shortname;
    }

    ///////////////////////////////////////////////////////////////////////////

    public PlayerCapabilities getPlayerCapabilities() {
		return pc;
	}
    
    public String getShortName() {
		return shortname;
	}
    
    public String getFullName() {
		return fullname;
	}
    
    public int getId() {
        return id;
    }
}
