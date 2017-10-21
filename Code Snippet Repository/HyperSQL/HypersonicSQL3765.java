    public int getNumericPrecisionInRadix() {

        switch (typeCode) {

            case Types.TINYINT :
                return 8;

            case Types.SQL_SMALLINT :
                return 16;

            case Types.SQL_INTEGER :
                return 32;

            case Types.SQL_BIGINT :
                return 64;

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE :
                return 64;

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return (int) precision;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }
