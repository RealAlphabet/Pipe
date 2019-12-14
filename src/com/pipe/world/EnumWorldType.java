package com.pipe.world;

public enum EnumWorldType {

    DEFAULT("default"),
    FLAT("flat"),
    LARGES_BIOMES("largeBiomes"),
    AMPLIFIED("amplified"),
    CUSTOMIZED("customized");

    ///////////////////////////////////////////////////////////////////////////

    private String name;

    ///////////////////////////////////////////////////////////////////////////

    EnumWorldType(String name) {
        this.name = name;
    }

    ///////////////////////////////////////////////////////////////////////////

    public String getName() {
        return name;
    }
}
