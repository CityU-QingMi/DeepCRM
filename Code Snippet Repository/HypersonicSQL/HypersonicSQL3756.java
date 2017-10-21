    public static double toDouble(Object a) {

        double value;

        if (a instanceof java.lang.Double) {
            return ((Double) a).doubleValue();
        } else if (a instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) a;

            value = bd.doubleValue();

            int        signum = bd.signum();
            BigDecimal bdd    = new BigDecimal(value + signum);

            if (bdd.compareTo(bd) != signum) {
                throw Error.error(ErrorCode.X_22003);
            }
        } else if (a instanceof Number) {
            value = ((Number) a).doubleValue();
        } else {
            throw Error.error(ErrorCode.X_22501);
        }

        return value;
    }
