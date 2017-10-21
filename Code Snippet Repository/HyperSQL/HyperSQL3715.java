    public Object newInterval(String s, IntervalType type) {

        intervalPosition  = 0;
        fractionPrecision = 0;
        intervalString    = s;

        boolean negate   = scanIntervalSign();
        long    units    = scanIntervalValue(type);
        int     fraction = 0;

        if (type.endIntervalType == Types.SQL_INTERVAL_SECOND) {
            fraction = scanIntervalFraction(type.scale);
        }

        if (intervalPosition != s.length()) {
            throw Error.error(ErrorCode.X_22006);
        }

        if (negate) {
            units    = -units;
            fraction = -fraction;
        }

        dateTimeType = type;

        if (type.defaultPrecision) {
            dateTimeType = IntervalType.getIntervalType(type.typeCode,
                    type.startIntervalType, type.endIntervalType,
                    intervalPrecision, fractionPrecision, false);
        }

        if (type.endPartIndex <= DTIType.INTERVAL_MONTH_INDEX) {
            return new IntervalMonthData(units);
        } else {
            return new IntervalSecondData(units, fraction);
        }
    }
