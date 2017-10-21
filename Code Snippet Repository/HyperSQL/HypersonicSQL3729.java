    public BigDecimal getSecondPart(Object interval) {

        long seconds = ((IntervalSecondData) interval).units;

        if (typeCode != Types.SQL_INTERVAL_SECOND) {
            seconds %= 60;
        }

        int nanos = ((IntervalSecondData) interval).nanos;

        return getSecondPart(seconds, nanos);
    }
