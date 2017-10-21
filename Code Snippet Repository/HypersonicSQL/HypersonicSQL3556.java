    public int canMoveFrom(Type otherType) {

        if (otherType == this) {
            return 0;
        }

        if (!otherType.isBinaryType()) {
            return -1;
        }

        switch (otherType.typeCode) {

            case Types.SQL_VARBINARY : {
                return 1;
            }
            case Types.SQL_BIT :
            case Types.SQL_BIT_VARYING :
            case Types.SQL_BLOB : {
                return -1;
            }
            case Types.SQL_GUID :
            case Types.SQL_BINARY : {
                return precision == otherType.precision ? 0
                                                        : -1;
            }
            default :
                return -1;
        }
    }
