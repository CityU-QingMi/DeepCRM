    public static IntervalType newIntervalType(int type, long precision,
            int fractionPrecision) {

        int startType = getStartIntervalType(type);
        int endType   = getEndIntervalType(type);
        int group = startType > Types.SQL_INTERVAL_MONTH
                    ? Types.SQL_INTERVAL_SECOND
                    : Types.SQL_INTERVAL_MONTH;

        return new IntervalType(group, type, precision, fractionPrecision,
                                startType, endType, false);
    }
