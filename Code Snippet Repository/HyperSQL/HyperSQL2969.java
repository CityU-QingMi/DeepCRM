    long getMillis(Object dateTime) {

        long millis;

        if (typeCode == Types.SQL_TIME
                || typeCode == Types.SQL_TIME_WITH_TIME_ZONE) {
            millis =
                (((TimeData) dateTime).getSeconds() + ((TimeData) dateTime)
                    .getZone()) * 1000L;
        } else {
            millis =
                (((TimestampData) dateTime)
                    .getSeconds() + ((TimestampData) dateTime).getZone()) * 1000;
        }

        return millis;
    }
