package com.pipe.netty.packet;

import com.pipe.netty.PacketBuffer;

import java.io.IOException;

public interface Packet<T> {

    default void read(PacketBuffer buf) throws IOException {
    }

    default void write(PacketBuffer buf) throws IOException {
    }

    default void process(T handler) {
    }
}
