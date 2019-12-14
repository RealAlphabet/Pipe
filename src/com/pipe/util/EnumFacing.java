package com.pipe.util;

public enum EnumFacing {

    DOWN(0, 1),
    UP(1, 0),
    NORTH(2, 3),
    SOUTH(3, 2),
    WEST(4, 5),
    EAST(5, 4);

    ///////////////////////////////////////////////////////////////////////////

    private int index;
    private int opposite;

    ///////////////////////////////////////////////////////////////////////////

    EnumFacing(int index, int opposite) {
        this.index = index;
        this.opposite = opposite;
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getIndex() {
        return index;
    }

    public int getOpposite() {
        return opposite;
    }
}
