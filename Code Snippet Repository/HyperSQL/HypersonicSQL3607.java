    public int getJDBCTypeCode() {

        switch (typeCode) {

            case Types.SQL_CHAR :
                return Types.CHAR;

            case Types.SQL_VARCHAR :
                return Types.VARCHAR;

            case Types.SQL_CLOB :
                return Types.CLOB;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "CharacterType");
        }
    }
