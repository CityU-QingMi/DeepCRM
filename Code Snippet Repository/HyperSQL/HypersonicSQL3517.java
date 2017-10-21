    public int canMoveFrom(Type otherType) {

        if (otherType == this) {
            return 0;
        }

        if (!otherType.isArrayType()) {
            return -1;
        }

        if (maxCardinality >= ((ArrayType) otherType).maxCardinality) {
            return dataType.canMoveFrom(otherType);
        } else {
            if (dataType.canMoveFrom(otherType) == -1) {
                return -1;
            }

            return 1;
        }
    }
