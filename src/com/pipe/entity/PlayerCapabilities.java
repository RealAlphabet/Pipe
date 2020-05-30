package com.pipe.entity;

public class PlayerCapabilities {
	
	private float walkspeed,flyspeed;
	private boolean flying,allowflying,creativemode;
	
	public PlayerCapabilities(float walkspeed, float flyspeed, boolean flying, boolean allowflying, boolean creativemode) {
		this.allowflying = allowflying;
		this.flyspeed = flyspeed;
		this.walkspeed = walkspeed;
		this.flying = flying;
		this.creativemode = creativemode;
	}
	
	public float getFlySpeed() {
		return flyspeed;
	}
	
	public float getWalkSpeed() {
		return walkspeed;
	}
	
	public boolean isAllowFlying() {
		return allowflying;
	}
	
	public boolean isCreativeMode() {
		return creativemode;
	}
	
	public boolean isFlying() {
		return flying;
	}

}
