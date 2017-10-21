    public Object convertToTypeLimits(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        switch (typeCode) {

            case Types.TINYINT :
            case Types.SQL_SMALLINT :
            case Types.SQL_INTEGER :
            case Types.SQL_BIGINT :
                return a;

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE :
                return a;

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL : {
                BigDecimal dec = (BigDecimal) a;

                if (scale != dec.scale()) {
                    dec = dec.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
                }

                int p = JavaSystem.precision(dec);

                if (p > precision) {
                    throw Error.error(ErrorCode.X_22003);
                }

                return dec;
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }
