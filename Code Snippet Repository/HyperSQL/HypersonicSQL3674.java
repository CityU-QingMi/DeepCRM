    public Object getValue(long seconds, int nanos, int zoneSeconds) {

        switch (typeCode) {

            case Types.SQL_DATE :
                seconds =
                    HsqlDateTime.getNormalisedDate(
                        (seconds + zoneSeconds) * 1000) / 1000;

                return new TimestampData(seconds);

            case Types.SQL_TIME_WITH_TIME_ZONE :
                seconds = HsqlDateTime.getNormalisedDate(seconds * 1000)
                          / 1000;

                return new TimeData((int) seconds, nanos, zoneSeconds);

            case Types.SQL_TIME :
                seconds =
                    HsqlDateTime.getNormalisedTime(
                        (seconds + zoneSeconds) * 1000) / 1000;

                return new TimeData((int) seconds, nanos);

            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                return new TimestampData(seconds, nanos, zoneSeconds);

            case Types.SQL_TIMESTAMP :
                return new TimestampData(seconds + zoneSeconds, nanos);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "DateTimeType");
        }
    }
