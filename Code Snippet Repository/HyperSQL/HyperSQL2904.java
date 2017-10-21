    public String getFullNameString() {

        switch (typeCode) {

            case Types.SQL_CHAR :
                return Tokens.T_CHARACTER;

            case Types.SQL_VARCHAR :
                return "CHARACTER VARYING";

            case Types.SQL_CLOB :
                return "CHARACTER LARGE OBJECT";

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "CharacterType");
        }
    }
