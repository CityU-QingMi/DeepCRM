    private String getNameStringPrivate() {

        switch (typeCode) {

            case Types.SQL_CHAR :
                return Tokens.T_CHARACTER;

            case Types.SQL_VARCHAR :
                return Tokens.T_VARCHAR;

            case Types.SQL_CLOB :
                return Tokens.T_CLOB;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "CharacterType");
        }
    }
