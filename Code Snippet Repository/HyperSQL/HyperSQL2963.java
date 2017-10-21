    public Object round(Object a, int part) {

        if (a == null) {
            return null;
        }

        long millis = getMillis(a);

        millis = HsqlDateTime.getRoundedPart(millis, part);
        millis -= getZoneMillis(a);

        switch (typeCode) {

            case Types.SQL_TIME_WITH_TIME_ZONE :
            case Types.SQL_TIME : {
                millis = HsqlDateTime.getNormalisedTime(millis);

                return new TimeData((int) (millis / 1000), 0,
                                    ((TimeData) a).getZone());
            }
            case Types.SQL_DATE :
            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
            case Types.SQL_TIMESTAMP : {
                return new TimestampData(millis / 1000, 0,
                                         ((TimestampData) a).getZone());
            }
            default :
        }

        throw Error.runtimeError(ErrorCode.U_S0500, "DateTimeType");
    }
