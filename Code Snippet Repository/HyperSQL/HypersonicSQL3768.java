    public int compareToZero(Object a) {

        if (a == null) {
            return 0;
        }

        switch (typeCode) {

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE : {
                double ad = ((Number) a).doubleValue();

                return ad == 0 ? 0
                               : ad < 0 ? -1
                                        : 1;
            }
            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return ((BigDecimal) a).signum();

            case Types.TINYINT :
            case Types.SQL_SMALLINT :
            case Types.SQL_INTEGER : {
                int ai = ((Number) a).intValue();

                return ai == 0 ? 0
                               : ai < 0 ? -1
                                        : 1;
            }
            case Types.SQL_BIGINT : {
                long al = ((Number) a).longValue();

                return al == 0 ? 0
                               : al < 0 ? -1
                                        : 1;
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }
