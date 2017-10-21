    public Object multiply(Object a, Object b) {

        if (a == null || b == null) {
            return null;
        }

        switch (typeCode) {

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE : {
                double ad = ((Number) a).doubleValue();
                double bd = ((Number) b).doubleValue();

                return ValuePool.getDouble(Double.doubleToLongBits(ad * bd));
            }
            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL : {
                if (!(a instanceof BigDecimal)) {
                    a = convertToDefaultType(null, a);
                }

                if (!(b instanceof BigDecimal)) {
                    b = convertToDefaultType(null, b);
                }

                BigDecimal abd = (BigDecimal) a;
                BigDecimal bbd = (BigDecimal) b;
                BigDecimal bd  = abd.multiply(bbd);

                return convertToTypeLimits(null, bd);
            }
            case Types.TINYINT :
            case Types.SQL_SMALLINT :
            case Types.SQL_INTEGER : {
                int ai = ((Number) a).intValue();
                int bi = ((Number) b).intValue();

                return ValuePool.getInt(ai * bi);
            }
            case Types.SQL_BIGINT : {
                long longa = ((Number) a).longValue();
                long longb = ((Number) b).longValue();

                return ValuePool.getLong(longa * longb);
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }
