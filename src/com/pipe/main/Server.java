package com.pipe.main;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.pipe.entity.EntityPlayer;
import com.pipe.entity.Player;
import com.pipe.netty.EnumConnectionState;
import com.pipe.netty.NetworkManager;
import com.pipe.netty.coder.PacketDecoder;
import com.pipe.netty.coder.PacketEncoder;
import com.pipe.netty.coder.VarintFrameDecoder;
import com.pipe.netty.coder.VarintFrameEncoder;
import com.pipe.netty.handler.NetHandlerHandshake;
import com.pipe.netty.packet.play.*;
import com.pipe.util.MathHelper;
import com.pipe.util.text.ITextComponent;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Server {

    private static Server SERVER;
    public static List<EntityPlayer> playerList = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////

    public static Server getServer() {
        return SERVER;
    }

    ///////////////////////////////////////////////////////////////////////////

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private Channel listener;
    private boolean running;
    private int teleportTicks;

    ///////////////////////////////////////////////////////////////////////////

    public Server() {
        SERVER = this;
    }

    public void start() {
        running = true;

        ///////////////

        if (Epoll.isAvailable()) {
            bossGroup = new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Boss IO Thread #%1$d").build());
            workGroup = new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Worker IO Thread #%1$d").build());

        } else {
            bossGroup = new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Boss IO Thread #%1$d").build());
            workGroup = new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Worker IO Thread #%1$d").build());
        }

        ///////////////

        ChannelFuture future = new ServerBootstrap()
                .group(bossGroup, workGroup)
                .channel((Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class))
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) {
                        channel.pipeline()
                                .addLast(new ReadTimeoutHandler(30))
                                .addLast(new VarintFrameDecoder())
                                .addLast(new PacketDecoder())
                                .addLast(new VarintFrameEncoder())
                                .addLast(new PacketEncoder());

                        NetworkManager networkManager = new NetworkManager();
                        networkManager.setNetHandler(new NetHandlerHandshake(networkManager));

                        channel.attr(NetworkManager.PROTOCOL_ATTRIBUTE_KEY).set(EnumConnectionState.HANDSHAKING);
                        channel.pipeline().addLast(networkManager);
                    }
                })
                .bind(25565)
                .awaitUninterruptibly();

        ///////////////

        if (future.isSuccess()) {
            listener = future.channel();

            System.out.println("Listening on 25565 port.");

            ///////////////

            long tickTime = 1000L / 20L;
            long currentTime = 0L;
            long prevTick = 0L;

            while (running) {
                if (currentTime - prevTick > tickTime) {
                    prevTick = currentTime;
                    update();
                }

                currentTime = System.currentTimeMillis();
            }

        } else {
            System.err.println("Could not bind to 25565 port.");
        }
    }

    public synchronized void stop() {
        new Thread("Shutdown Thread") {

            @Override
            public void run() {
                try {
                    listener.close().syncUninterruptibly();

                } catch (ChannelException ex) {
                    System.out.println("§cCould not close listen thread");
                }

                ///////////////

                // TODO disconnect all clients with a specific message

//                Server.this.connectionLock.readLock().lock();
//
//                try {
//                    for (UserConnection protocol : Server.this.connections) {
//                        protocol.getHandle().close(new SMessage("Eagle", ComponentSerializer.toString(new TextComponent("\n§6Redémarrage du serveur de Eagle, à tout de suite !\n "))));
//                    }
//
//                } finally {
//                    Server.this.connectionLock.readLock().unlock();
//                }

                ///////////////

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                bossGroup.shutdownGracefully();
                workGroup.shutdownGracefully();

                while (true) {
                    try {
                        bossGroup.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                        workGroup.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                        break;

                    } catch (InterruptedException ignore) {
                    }
                }

                ///////////////

                running = false;
            }

        }.start();
    }

    ///////////////////////////////////////////////////////////////////////////

    public void update() {
        for (EntityPlayer player : playerList) {
            player.connection.sendPacket(new SPacketKeepAlive(new Random().nextInt()));

            short encodedPosX = (short) ((player.posX * 32 - player.prevPosX * 32) * 128);
            short encodedPosY = (short) ((player.posY * 32 - player.prevPosY * 32) * 128);
            short encodedPosZ = (short) ((player.posZ * 32 - player.prevPosZ * 32) * 128);

            int encodedYaw = MathHelper.floor(player.yaw * 256.0F / 360.0F);
            int encodedPitch = MathHelper.floor(player.pitch * 256.0F / 360.0F);

            boolean moving = encodedPosX != 0 || encodedPosY != 0 || encodedPosZ != 0;
            boolean rotating = player.prevYaw != player.yaw || player.prevPitch != player.pitch;

            for (EntityPlayer other : playerList) {
                if (player == other) continue;

                // UPDATE POSITION
                if (teleportTicks == 400) {
                    other.connection.sendPacket(new SPacketEntityTeleport(player.id, player.posX, player.posY, player.posZ, (byte) encodedYaw, (byte) encodedPitch, true));

                } else {
                    if (moving) {
//                        if (rotating) {
//                            other.connection.sendPacket(new SPacketEntity.SPacketEntityLookMove(player.id, encodedPosX, encodedPosY, encodedPosZ, (byte) encodedYaw, (byte) encodedPitch, true));
//
//                        } else {
                        other.connection.sendPacket(new SPacketEntity.SPacketEntityRelMove(player.id, encodedPosX, encodedPosY, encodedPosZ, true));
//                        }

                    } else if (rotating) {
                        other.connection.sendPacket(new SPacketEntity.SPacketEntityLook(player.id, (byte) encodedYaw, (byte) encodedPitch, true));
                    }
                }

                other.connection.sendPacket(new SPacketEntityHeadLook(player, (byte) encodedYaw));
                other.connection.sendPacket(new SPacketEntityMetadata(player.id, player.FLAGS));
            }

            player.prevPosX = player.posX;
            player.prevPosY = player.posY;
            player.prevPosZ = player.posZ;
            player.prevYaw = player.yaw;
            player.prevPitch = player.pitch;
        }

        if (teleportTicks == 400)
            teleportTicks = 0;

        else {
            teleportTicks++;
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    //  BROADCAST
    ///////////////////////////////////////////////////////////////////////////

    public void broadcastMessage(String message) {
        playerList.forEach(player -> player.sendMessage(message));
    }

    public void broadcastMessage(ITextComponent component) {
        playerList.forEach(player -> player.sendMessage(component));
    }


    ///////////////////////////////////////////////////////////////////////////
    //  PLAYERS
    ///////////////////////////////////////////////////////////////////////////

    public Collection<Player> getOnlinePlayers() {
        return Collections.unmodifiableCollection(playerList);
    }

    public Player getPlayerByName(String name) {
        return playerList.stream().filter(player -> player.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Player getPlayerByUUID(UUID uuid) {
        return playerList.stream().filter(player -> player.getUUID() == uuid).findFirst().orElse(null);
    }

    public void registerConnection(EntityPlayer player) {
        playerList.add(player);

        for (EntityPlayer other : playerList) {
            if (other == player) return;

            other.connection.sendPacket(new SPacketPlayerListItem(player.profile, SPacketPlayerListItem.Action.ADD_PLAYER));
            other.connection.sendPacket(new SPacketSpawnPlayer(player.id, player.profile.getId(), player.posX, player.posY, player.posZ, (byte) player.yaw, (byte) player.pitch));

            player.connection.sendPacket(new SPacketPlayerListItem(other.profile, SPacketPlayerListItem.Action.ADD_PLAYER));
            player.connection.sendPacket(new SPacketSpawnPlayer(other.id, other.profile.getId(), other.posX, other.posY, other.posZ, (byte) other.yaw, (byte) other.pitch));
        }
    }

    public void unregisterConnection(EntityPlayer player) {
        System.out.println(player.getName() + " s'est déconnecté !");

        for (EntityPlayer other : playerList) {
            other.connection.sendPacket(new SPacketPlayerListItem(player.profile, SPacketPlayerListItem.Action.REMOVE_PLAYER));
            other.connection.sendPacket(new SPacketDestroyEntities(player.id));
        }

        playerList.remove(player);
    }
}
