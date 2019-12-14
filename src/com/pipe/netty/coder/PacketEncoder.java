package com.pipe.netty.coder;

import com.pipe.netty.EnumConnectionState;
import com.pipe.netty.EnumPacketDirection;
import com.pipe.netty.NetworkManager;
import com.pipe.netty.PacketBuffer;
import com.pipe.netty.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext context, Packet packet, ByteBuf buf) throws IOException {
        EnumConnectionState state = context.channel().attr(NetworkManager.PROTOCOL_ATTRIBUTE_KEY).get();

        if (state == null) {
            throw new RuntimeException("Connection protocol unknown: " + context.toString());

        } else {
            PacketBuffer packetbuffer = new PacketBuffer(buf);
            Integer id = state.getPacketId(EnumPacketDirection.CLIENTBOUND, packet);

            if (id == null) {
                throw new IOException("Can't serialize unregistered packet.");

            } else {
                packetbuffer.writeVarIntToBuffer(id);
                packet.write(packetbuffer);
            }
        }
    }
}
