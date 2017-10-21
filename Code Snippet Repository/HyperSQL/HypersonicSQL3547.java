    public int precedenceDegree(Type other) {

        if (other.typeCode == typeCode) {
            return 0;
        }

        if (!other.isBinaryType()) {
            return Integer.MIN_VALUE;
        }

        return other.typeCode == Types.SQL_BLOB ? 4
                                                : 2;
    }
