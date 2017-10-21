    public static int compareBigDecimalToLongLimits(Object value) {

        if (value instanceof BigDecimal) {
            int compare = compareToLongLimits((BigDecimal) value);

            return compare;
        }

        return 0;
    }
