    public static NumberType getNumberType(int type, long precision,
                                           int scale) {

        switch (type) {

            case Types.SQL_INTEGER :
                return SQL_INTEGER;

            case Types.SQL_SMALLINT :
                return SQL_SMALLINT;

            case Types.SQL_BIGINT :
                return SQL_BIGINT;

            case Types.TINYINT :
                return TINYINT;

            case Types.SQL_REAL :
            case Types.SQL_DOUBLE :
                return SQL_DOUBLE;

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return new NumberType(type, precision, scale);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }
