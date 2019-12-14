package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

import java.util.Set;

public class SPacketPlayerPosLook implements Packet<INetHandlerPlay> {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private Set<EnumFlags> flags;
    private int teleportId;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketPlayerPosLook(double x, double y, double z, float yaw, float pitch, Set<EnumFlags> flags, int teleportId) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.flags = flags;
        this.teleportId = teleportId;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeFloat(yaw);
        buf.writeFloat(pitch);
        buf.writeByte(EnumFlags.pack(flags));
        buf.writeVarIntToBuffer(teleportId);
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }

    ///////////////////////////////////////////////////////////////////////////

    public enum EnumFlags {

        X(0),
        Y(1),
        Z(2),
        Y_ROT(3),
        X_ROT(4);

        ///////////////////////////////////////////////////////////////////////////

        private int bit;

        ///////////////////////////////////////////////////////////////////////////

        EnumFlags(int bit) {
            this.bit = bit;
        }

        ///////////////////////////////////////////////////////////////////////////

        private int getMask() {
            return 1 << this.bit;
        }

        private boolean isSet(int flags) {
            return (flags & this.getMask()) == this.getMask();
        }

        ///////////////////////////////////////////////////////////////////////////

        public static int pack(Set<SPacketPlayerPosLook.EnumFlags> flags) {
            int i = 0;

            for (SPacketPlayerPosLook.EnumFlags spacketplayerposlook$enumflags : flags) {
                i |= spacketplayerposlook$enumflags.getMask();
            }

            return i;
        }
    }
}
