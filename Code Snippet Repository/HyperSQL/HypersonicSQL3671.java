    long getZoneMillis(Object dateTime) {

        long millis;

        if (typeCode == Types.SQL_TIME
                || typeCode == Types.SQL_TIME_WITH_TIME_ZONE) {
            millis = ((TimeData) dateTime).getZone() * 1000L;
        } else {
            millis = ((TimestampData) dateTime).getZone() * 1000L;
        }

        return millis;
    }
