    public int precedenceDegree(Type other) {

        if (other.typeCode == typeCode) {
            return 0;
        }

        if (!other.isCharacterType()) {
            return Integer.MIN_VALUE;
        }

        switch (typeCode) {

            case Types.SQL_CHAR :
                return other.typeCode == Types.SQL_CLOB ? 4
                                                        : 2;

            case Types.SQL_VARCHAR :
                return other.typeCode == Types.SQL_CLOB ? 4
                                                        : 2;

            case Types.SQL_CLOB :
                return other.typeCode == Types.SQL_CHAR ? -4
                                                        : -2;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "CharacterType");
        }
    }
