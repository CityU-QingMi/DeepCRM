    public int getDecimalPrecision() {

        switch (typeCode) {

            case Types.TINYINT :
                return tinyintPrecision;

            case Types.SQL_SMALLINT :
                return smallintPrecision;

            case Types.SQL_INTEGER :
                return integerPrecision;

            case Types.SQL_BIGINT :
                return bigintPrecision;

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE :
                return displaySize() - 1;

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return (int) precision;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }
