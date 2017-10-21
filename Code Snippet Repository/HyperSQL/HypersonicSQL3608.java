    public static CharacterType getCharacterType(int type, long length) {

        switch (type) {

            case Types.SQL_CHAR :
                if (length < fixedTypesLength) {
                    return charArray[(int) length];
                }

            // fall through
            case Types.SQL_VARCHAR :
                return new CharacterType(type, (int) length);

            case Types.SQL_CLOB :
                return new ClobType(length);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "CharacterType");
        }
    }
