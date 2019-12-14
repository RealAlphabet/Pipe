package com.pipe.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pipe.util.text.ITextComponent;

import java.lang.reflect.Type;

public class ServerStatusResponse {
    private ITextComponent description;
    private ServerStatusResponse.Players players;
    private ServerStatusResponse.Version version;
    private String favicon;

    public ITextComponent getServerDescription() {
        return this.description;
    }

    public void setServerDescription(ITextComponent descriptionIn) {
        this.description = descriptionIn;
    }

    public ServerStatusResponse.Players getPlayers() {
        return this.players;
    }

    public void setPlayers(ServerStatusResponse.Players playersIn) {
        this.players = playersIn;
    }

    public ServerStatusResponse.Version getVersion() {
        return this.version;
    }

    public void setVersion(ServerStatusResponse.Version versionIn) {
        this.version = versionIn;
    }

    public void setFavicon(String faviconBlob) {
        this.favicon = faviconBlob;
    }

    public String getFavicon() {
        return this.favicon;
    }

    public static class Players {
        private final int maxPlayers;
        private final int onlinePlayerCount;

        public Players(int maxOnlinePlayers, int onlinePlayers) {
            this.maxPlayers = maxOnlinePlayers;
            this.onlinePlayerCount = onlinePlayers;
        }

        public int getMaxPlayers() {
            return this.maxPlayers;
        }

        public int getOnlinePlayerCount() {
            return this.onlinePlayerCount;
        }

        public static class Serializer implements JsonSerializer<Players> {
            public JsonElement serialize(ServerStatusResponse.Players p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("max", p_serialize_1_.getMaxPlayers());
                jsonobject.addProperty("online", p_serialize_1_.getOnlinePlayerCount());
                return jsonobject;
            }
        }
    }

    public static class Serializer implements JsonSerializer<ServerStatusResponse> {
        public JsonElement serialize(ServerStatusResponse p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject jsonobject = new JsonObject();

            if (p_serialize_1_.getServerDescription() != null) {
                jsonobject.add("description", p_serialize_3_.serialize(p_serialize_1_.getServerDescription()));
            }

            if (p_serialize_1_.getPlayers() != null) {
                jsonobject.add("players", p_serialize_3_.serialize(p_serialize_1_.getPlayers()));
            }

            if (p_serialize_1_.getVersion() != null) {
                jsonobject.add("version", p_serialize_3_.serialize(p_serialize_1_.getVersion()));
            }

            if (p_serialize_1_.getFavicon() != null) {
                jsonobject.addProperty("favicon", p_serialize_1_.getFavicon());
            }

            return jsonobject;
        }
    }

    public static class Version {
        private final String name;
        private final int protocol;

        public Version(String nameIn, int protocolIn) {
            this.name = nameIn;
            this.protocol = protocolIn;
        }

        public String getName() {
            return this.name;
        }

        public int getProtocol() {
            return this.protocol;
        }

        public static class Serializer implements JsonSerializer<ServerStatusResponse.Version> {
            public JsonElement serialize(ServerStatusResponse.Version p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("name", p_serialize_1_.getName());
                jsonobject.addProperty("protocol", Integer.valueOf(p_serialize_1_.getProtocol()));
                return jsonobject;
            }
        }
    }
}
