    private IntervalSecondData subtract(long aSeconds, long bSeconds,
                                        long nanos) {

        if (endIntervalType != Types.SQL_INTERVAL_SECOND) {
            aSeconds =
                HsqlDateTime.getTruncatedPart(aSeconds * 1000, endIntervalType)
                / 1000;
            bSeconds =
                HsqlDateTime.getTruncatedPart(bSeconds * 1000, endIntervalType)
                / 1000;
            nanos = 0;
        }

        return new IntervalSecondData(aSeconds - bSeconds, nanos, this, true);
    }
