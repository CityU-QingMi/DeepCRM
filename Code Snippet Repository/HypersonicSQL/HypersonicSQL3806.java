    public int precedenceDegree(Type other) {

        if (other.typeCode == typeCode) {
            if (typeCode == Types.SQL_ARRAY) {
                return collectionBaseType().precedenceDegree(
                    other.collectionBaseType());
            }

            return 0;
        }

        return Integer.MIN_VALUE;
    }
