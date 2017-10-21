    public static int compareToLongLimits(BigDecimal value) {

        if (NumberType.MIN_LONG.compareTo(value) > 0) {
            return -1;
        } else if (NumberType.MAX_LONG.compareTo(value) < 0) {
            return 1;
        }

        return 0;
    }
