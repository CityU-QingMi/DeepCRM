    public TimestampData newDate(String s) {

        intervalPosition  = 0;
        fractionPrecision = 0;
        dateTimeType      = null;
        intervalString    = s;

        scanDateParts(2);

        if (intervalPosition != s.length()) {
            throw Error.error(ErrorCode.X_22007);
        }

        long seconds = HsqlDateTime.getDateSeconds(s);

        return new TimestampData(seconds);
    }
