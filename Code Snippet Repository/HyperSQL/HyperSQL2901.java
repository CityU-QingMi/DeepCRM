    public static CharacterType getCharacterType(int type, long length,
            Collation collation) {

        if (collation == null) {
            collation = Collation.getDefaultInstance();
        }

        switch (type) {

            case Types.SQL_VARCHAR :
            case Types.SQL_CHAR :
                return new CharacterType(collation, type, (int) length);

            case Types.SQL_CLOB :
                CharacterType typeObject = new ClobType(length);

                typeObject.collation = collation;

                return typeObject;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "CharacterType");
        }
    }
