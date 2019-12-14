package com.pipe.netty.coder;

import com.pipe.netty.EnumPacketDirection;
import com.pipe.netty.NetworkManager;
import com.pipe.netty.PacketBuffer;
import com.pipe.netty.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.IOException;
import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf buf, List<Object> out) throws Exception {
        if (buf.readableBytes() > 0) {
            PacketBuffer packetBuffer = new PacketBuffer(buf);

            int i = packetBuffer.readVarIntFromBuffer();
            Packet packet = context.channel().attr(NetworkManager.PROTOCOL_ATTRIBUTE_KEY).get().getPacket(EnumPacketDirection.SERVERBOUND, i);

            if (packet == null) {
                throw new IOException("Bad packet id " + i);

            } else {
                packet.read(packetBuffer);

                if (packetBuffer.readableBytes() > 0) {
                    throw new IOException("Packet (" + packet.getClass().getSimpleName() + ") was larger than I expected, found " + packetBuffer.readableBytes() + " bytes extra whilst reading packet " + i);

                } else {
                    out.add(packet);
                }
            }
        }
    }
}
