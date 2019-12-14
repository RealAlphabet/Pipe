package com.pipe.netty.coder;

import com.pipe.netty.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

public class VarintFrameDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf buf, List<Object> out) {
        buf.markReaderIndex();
        byte[] abyte = new byte[3];

        for (int i = 0; i < abyte.length; ++i) {
            if (!buf.isReadable()) {
                buf.resetReaderIndex();
                return;
            }

            abyte[i] = buf.readByte();

            if (abyte[i] >= 0) {
                PacketBuffer packetbuffer = new PacketBuffer(Unpooled.wrappedBuffer(abyte));

                try {
                    int j = packetbuffer.readVarIntFromBuffer();

                    if (buf.readableBytes() >= j) {
                        out.add(buf.readBytes(j));
                        return;
                    }

                    buf.resetReaderIndex();
                } finally {
                    packetbuffer.release();
                }

                return;
            }
        }

        throw new CorruptedFrameException("length wider than 21-bit");
    }
}
