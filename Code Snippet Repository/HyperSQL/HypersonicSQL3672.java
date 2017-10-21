    public BigDecimal getSecondPart(Object dateTime) {

        long seconds = getPart(null, dateTime, Types.SQL_INTERVAL_SECOND);
        int  nanos   = 0;

        if (typeCode == Types.SQL_TIMESTAMP
                || typeCode == Types.SQL_TIMESTAMP_WITH_TIME_ZONE) {
            nanos = ((TimestampData) dateTime).getNanos();
        } else if (typeCode == Types.SQL_TIME
                   || typeCode == Types.SQL_TIME_WITH_TIME_ZONE) {
            nanos = ((TimeData) dateTime).getNanos();
        }

        return getSecondPart(seconds, nanos);
    }
