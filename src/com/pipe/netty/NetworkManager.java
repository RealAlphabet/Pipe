package com.pipe.netty;

import com.pipe.netty.handler.INetHandler;
import com.pipe.netty.packet.Packet;
import com.pipe.util.text.TextComponentString;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

public class NetworkManager extends SimpleChannelInboundHandler<Packet<INetHandler>> {

    public static final AttributeKey<EnumConnectionState> PROTOCOL_ATTRIBUTE_KEY = AttributeKey.valueOf("protocol");

    ///////////////////////////////////////////////////////////////////////////

    private Channel channel;
    private INetHandler handler;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        channel = ctx.channel();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        handler.onDisconnect(new TextComponentString("Disconnected."));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet<INetHandler> packet) {
        packet.process(handler);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (ctx.channel().isActive()) {
            cause.printStackTrace();
            ctx.close();
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    public void sendPacket(Packet packet) {
        if (channel.isOpen()) {
            channel.writeAndFlush(packet);
        }
    }

    public void closeChannel() {
        if (channel.isOpen()) {
            channel.close().awaitUninterruptibly();
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    public void setConnectionState(EnumConnectionState state) {
        channel.attr(PROTOCOL_ATTRIBUTE_KEY).set(state);
    }

    public void setNetHandler(INetHandler newHandler) {
        handler = newHandler;
    }

    public INetHandler getNetHandler() {
        return handler;
    }
}
