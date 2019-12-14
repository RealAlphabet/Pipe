package com.pipe.netty.coder;

import com.pipe.netty.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@Sharable
public class VarintFrameEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext context, ByteBuf buf, ByteBuf out) {
        int i = buf.readableBytes();
        int j = PacketBuffer.getVarIntSize(i);

        if (j > 3) {
            throw new IllegalArgumentException("unable to fit " + i + " into " + 3);
        } else {
            PacketBuffer packetbuffer = new PacketBuffer(out);
            packetbuffer.ensureWritable(j + i);
            packetbuffer.writeVarIntToBuffer(i);
            packetbuffer.writeBytes(buf, buf.readerIndex(), i);
        }
    }
}

