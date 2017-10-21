    public int canMoveFrom(Type otherType) {

        if (otherType == this) {
            return 0;
        }

        if (typeCode == otherType.typeCode) {
            return scale >= otherType.scale ? 0
                                            : -1;
        }

        return -1;
    }
