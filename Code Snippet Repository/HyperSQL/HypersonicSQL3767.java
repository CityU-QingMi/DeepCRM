    public static boolean isZero(Object a) {

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).signum() == 0;
        } else if (a instanceof Double) {
            return ((Double) a).doubleValue() == 0 || ((Double) a).isNaN();
        } else {
            return ((Number) a).longValue() == 0;
        }
    }
