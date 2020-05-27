package com.pipe.entity;

import com.pipe.util.world.Location;
import com.pipe.world.World;

import java.util.Collections;
import java.util.List;

public abstract class Entity {

    private static int entityCount = 1;

    ///////////////////////////////////////////////////////////////////////////

    public final int id;
    public final List<Entity> passengers;

    public World world;
    public double prevPosX;
    public double prevPosY;
    public double prevPosZ;
    public double prevYaw;
    public double prevPitch;
    public double posX;
    public double posY;
    public double posZ;
    public float yaw;
    public float pitch;

    public byte FLAGS;

    public float health;
    public float fallDistance;
    public boolean onGround;
    public boolean glowing;
    public boolean dead;

    public Entity() {
        this.id = entityCount++;
        this.passengers = Collections.emptyList();
        this.health = 20.0F;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  TELEPORTATION
    ///////////////////////////////////////////////////////////////////////////

    public abstract void teleport(Entity entity);

    public abstract void teleport(Location location);

    public abstract void teleport(double posX, double posY, double posZ);

    public abstract void teleport(double posX, double posY, double posZ, float yaw, float pitch);


    ///////////////////////////////////////////////////////////////////////////
    //  FLAGS
    ///////////////////////////////////////////////////////////////////////////

    public void setFlag(int flag, boolean value) {
        if (value)
            FLAGS = (byte) (FLAGS | (1 << flag));

        else {
            FLAGS = (byte) (FLAGS & ~(1 << flag));
        }
    }

    public boolean getFlag(int flag) {
        return (FLAGS & (1 << flag)) == 1;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  SETTERS
    ///////////////////////////////////////////////////////////////////////////

    public void setSneaking(boolean sneaking) {
        setFlag(1, sneaking);
    }

    public void setSprinting(boolean sprinting) {
        setFlag(3, sprinting);
    }

    public void setInvisible(boolean invisible) {
        setFlag(5, invisible);
    }

    public void setGlowing(boolean glowing) {
        setFlag(6, glowing);
    }


    ///////////////////////////////////////////////////////////////////////////
    //  GETTERS
    ///////////////////////////////////////////////////////////////////////////

    public Location getLocation() {
        return new Location(world, posX, posY, posZ, yaw, pitch);
    }

    public boolean isSneaking() {
        return getFlag(1);
    }

    public boolean isSprinting() {
        return getFlag(3);
    }

    public boolean isInvisible() {
        return getFlag(5);
    }

    public boolean isGlowing() {
        return getFlag(6);
    }
}
