    public int precedenceDegree(Type other) {

        if (other.typeCode == typeCode) {
            return 0;
        }

        if (!other.isBinaryType()) {
            return Integer.MIN_VALUE;
        }

        switch (typeCode) {

            case Types.SQL_BIT :
            case Types.SQL_BIT_VARYING :
                return Integer.MIN_VALUE;

            case Types.SQL_BINARY :
                return other.typeCode == Types.SQL_BLOB ? 4
                                                        : 2;

            case Types.SQL_VARBINARY :
                return other.typeCode == Types.SQL_BLOB ? 4
                                                        : 2;

            case Types.SQL_BLOB :
                return other.typeCode == Types.SQL_BINARY ? -4
                                                          : -2;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "BinaryType");
        }
    }
