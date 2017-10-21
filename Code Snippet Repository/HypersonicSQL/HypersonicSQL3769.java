    public static long scaledDecimal(Object a, int scale) {

        if (a == null) {
            return 0;
        }

        if (scale == 0) {
            return 0;
        }

        BigDecimal value = ((BigDecimal) a);

        if (value.scale() == 0) {
            return 0;
        }

        value = value.setScale(0, BigDecimal.ROUND_FLOOR);
        value = ((BigDecimal) a).subtract(value);

        return value.movePointRight(scale).longValue();
    }
