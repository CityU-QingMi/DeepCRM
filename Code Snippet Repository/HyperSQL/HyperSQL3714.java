    public TimeData newTime(String s) {

        intervalPosition  = 0;
        fractionPrecision = 0;
        dateTimeType      = null;
        intervalString    = s;

        long    seconds = scanIntervalValue(Type.SQL_INTERVAL_HOUR_TO_SECOND);
        int     fraction = scanIntervalFraction(DTIType.maxFractionPrecision);
        long    zoneSeconds = 0;
        int     position    = intervalPosition;
        boolean hasZone     = false;
        boolean negate      = scanIntervalSign();

        if (position != intervalPosition) {
            zoneSeconds = scanIntervalValue(Type.SQL_INTERVAL_HOUR_TO_MINUTE);
            hasZone     = true;
        }

        if (intervalPosition != s.length()) {
            throw Error.error(ErrorCode.X_22009);
        }

        if (seconds >= DTIType.yearToSecondFactors[2]) {
            throw Error.error(ErrorCode.X_22008);
        }

        if (zoneSeconds > DTIType.timezoneSecondsLimit) {
            throw Error.error(ErrorCode.X_22009);
        }

        if (negate) {
            zoneSeconds = -zoneSeconds;
        }

        int type = hasZone ? Types.SQL_TIME_WITH_TIME_ZONE
                           : Types.SQL_TIME;

        dateTimeType = DateTimeType.getDateTimeType(type, fractionPrecision);

        if (hasZone) {
            seconds -= zoneSeconds;
        }

        return new TimeData((int) seconds, fraction, (int) zoneSeconds);
    }
