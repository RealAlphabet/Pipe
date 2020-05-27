package com.pipe.util.math;

public class Vec3d {

    public static final Vec3d ZERO = new Vec3d(0.0D, 0.0D, 0.0D);

    public final double x;
    public final double y;
    public final double z;

    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}