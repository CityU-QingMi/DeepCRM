    public String getNameString() {

        switch (typeCode) {

            case Types.TINYINT :
                return Tokens.T_TINYINT;

            case Types.SQL_SMALLINT :
                return Tokens.T_SMALLINT;

            case Types.SQL_INTEGER :
                return Tokens.T_INTEGER;

            case Types.SQL_BIGINT :
                return Tokens.T_BIGINT;

            case Types.SQL_REAL :
                return Tokens.T_REAL;

            case Types.SQL_FLOAT :
                return Tokens.T_FLOAT;

            case Types.SQL_DOUBLE :
                return Tokens.T_DOUBLE;

            case Types.SQL_NUMERIC :
                return Tokens.T_NUMERIC;

            case Types.SQL_DECIMAL :
                return Tokens.T_DECIMAL;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }
