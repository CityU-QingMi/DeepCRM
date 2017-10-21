    public static int compareToLongLimits(BigInteger value) {

        if (NumberType.MIN_LONG_BI.compareTo(value) > 0) {
            return -1;
        } else if (NumberType.MAX_LONG_BI.compareTo(value) < 0) {
            return 1;
        }

        return 0;
    }
