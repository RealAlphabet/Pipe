package com.pipe.util.entity;

import com.pipe.netty.PacketBuffer;
import com.pipe.util.EnumFacing;
import com.pipe.util.text.ITextComponent;
import com.pipe.world.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class DataSerializer<T> {

    private static final List<Serializer> REGISTRY = new ArrayList<>(16);

    // NUMBER
    public static final Serializer<Boolean> BOOLEAN = PacketBuffer::writeBoolean;
    public static final Serializer<Byte> BYTE = PacketBuffer::writeByte;
    public static final Serializer<Integer> VARINT = PacketBuffer::writeVarIntToBuffer;
    public static final Serializer<Float> FLOAT = PacketBuffer::writeFloat;

    // TEXT
    public static final Serializer<String> STRING = PacketBuffer::writeString;
    public static final Serializer<ITextComponent> TEXT_COMPONENT = PacketBuffer::writeTextComponent;

    // POSITION
    public static final Serializer<BlockPos> BLOCK_POS = PacketBuffer::writeBlockPos;
    public static final Serializer<Byte> ROTATIONS = PacketBuffer::writeByte; // TODO ROTATIONS
    public static final Serializer<EnumFacing> FACING = PacketBuffer::writeEnumValue;

    // OTHERS TODO OPTIONALS
    public static final Serializer<Byte> OPTIONAL_ITEM_STACK = PacketBuffer::writeByte;
    public static final Serializer<Byte> OPTIONAL_BLOCK_POS = PacketBuffer::writeByte;
    public static final Serializer<Byte> OPTIONAL_UNIQUE_ID = PacketBuffer::writeByte;
    public static final Serializer<Byte> OPTIONAL_BLOCK_STATE = PacketBuffer::writeByte;

    // NBT TODO NBT
    public static final Serializer<Byte> NBT_TAG = PacketBuffer::writeByte;

    public static Serializer getSerializer(int index) {
        return REGISTRY.get(index);
    }

    static {
        REGISTRY.add(BYTE);
        REGISTRY.add(VARINT);
        REGISTRY.add(FLOAT);
        REGISTRY.add(STRING);
        REGISTRY.add(TEXT_COMPONENT);
        REGISTRY.add(OPTIONAL_ITEM_STACK);
        REGISTRY.add(BOOLEAN);
        REGISTRY.add(ROTATIONS);
        REGISTRY.add(BLOCK_POS);
        REGISTRY.add(OPTIONAL_BLOCK_POS);
        REGISTRY.add(FACING);
        REGISTRY.add(OPTIONAL_UNIQUE_ID);
        REGISTRY.add(OPTIONAL_BLOCK_STATE);
        REGISTRY.add(NBT_TAG);
    }

    public interface Serializer<T> {

        void write(PacketBuffer buf, T value);
    }
}
