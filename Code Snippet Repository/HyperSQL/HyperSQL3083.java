    public Object ceiling(Object a) {

        if (a == null) {
            return null;
        }

        switch (typeCode) {

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE : {
                double ad = Math.ceil(((Double) a).doubleValue());

                if (Double.isInfinite(ad)) {
                    throw Error.error(ErrorCode.X_22003);
                }

                return ValuePool.getDouble(Double.doubleToLongBits(ad));
            }
            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL : {
                BigDecimal value = ((BigDecimal) a).setScale(0,
                    BigDecimal.ROUND_CEILING);

                return value;
            }
            default :
                return a;
        }
    }
