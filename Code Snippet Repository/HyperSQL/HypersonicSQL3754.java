    static Long convertToLong(SessionInterface session, Object a) {

        if (a instanceof Integer) {
            return ValuePool.getLong(((Integer) a).intValue());
        } else if (a instanceof Long) {
            return (Long) a;
        } else if (a instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) a;

            if (bd.compareTo(MAX_LONG) > 0 || bd.compareTo(MIN_LONG) < 0) {
                throw Error.error(ErrorCode.X_22003);
            }

            return ValuePool.getLong(bd.longValue());
        } else if (a instanceof Double || a instanceof Float) {
            double d = ((Number) a).doubleValue();

            if (session instanceof Session) {
                if (!((Session) session).database.sqlConvertTruncate) {
                    d = java.lang.Math.rint(d);
                }
            }

            if (Double.isInfinite(d) || Double.isNaN(d)
                    || d >= (double) Long.MAX_VALUE + 1
                    || d <= (double) Long.MIN_VALUE - 1) {
                throw Error.error(ErrorCode.X_22003);
            }

            return ValuePool.getLong((long) d);
        } else {
            throw Error.error(ErrorCode.X_42561);
        }
    }
