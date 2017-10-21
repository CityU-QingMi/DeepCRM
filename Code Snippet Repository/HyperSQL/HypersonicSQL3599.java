    public int canMoveFrom(Type otherType) {

        if (otherType == this) {
            return 0;
        }

        if (!otherType.isCharacterType()) {
            return -1;
        }

        switch (typeCode) {

            case Types.SQL_VARCHAR : {
                if (otherType.typeCode == typeCode) {
                    return precision >= otherType.precision ? 0
                                                            : 1;
                }

                if (otherType.typeCode == Types.SQL_CHAR) {
                    return precision >= otherType.precision ? 0
                                                            : -1;
                }

                return -1;
            }
            case Types.SQL_CLOB : {
                if (otherType.typeCode == Types.SQL_CLOB) {
                    return precision >= otherType.precision ? 0
                                                            : 1;
                }

                return -1;
            }
            case Types.SQL_CHAR : {
                return otherType.typeCode == Types.SQL_CHAR
                       && precision == otherType.precision ? 0
                                                           : -1;
            }
            default :
                return -1;
        }
    }
