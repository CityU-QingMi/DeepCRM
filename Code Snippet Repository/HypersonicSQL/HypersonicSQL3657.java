    public Object convertSQLToJavaGMT(SessionInterface session, Object a) {

        long millis;

        switch (typeCode) {

            case Types.SQL_TIME :
            case Types.SQL_TIME_WITH_TIME_ZONE :
                millis = ((TimeData) a).getSeconds() * 1000L;
                millis += ((TimeData) a).getNanos() / 1000000;

                return new java.sql.Time(millis);

            case Types.SQL_DATE :
                millis = ((TimestampData) a).getSeconds() * 1000;

                return new java.sql.Date(millis);

            case Types.SQL_TIMESTAMP :
            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                millis = ((TimestampData) a).getSeconds() * 1000;

                java.sql.Timestamp value = new java.sql.Timestamp(millis);

                value.setNanos(((TimestampData) a).getNanos());

                return value;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "DateTimeType");
        }
    }
