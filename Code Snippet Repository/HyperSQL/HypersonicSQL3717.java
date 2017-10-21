    public Object add(Session session, Object a, Object b, Type otherType) {

        if (a == null || b == null) {
            return null;
        }

        switch (typeCode) {

            case Types.SQL_INTERVAL_YEAR :
            case Types.SQL_INTERVAL_YEAR_TO_MONTH :
            case Types.SQL_INTERVAL_MONTH :
                long months = ((IntervalMonthData) a).units
                              + ((IntervalMonthData) b).units;

                return new IntervalMonthData(months, this);

            case Types.SQL_INTERVAL_DAY :
            case Types.SQL_INTERVAL_DAY_TO_HOUR :
            case Types.SQL_INTERVAL_DAY_TO_MINUTE :
            case Types.SQL_INTERVAL_DAY_TO_SECOND :
            case Types.SQL_INTERVAL_HOUR :
            case Types.SQL_INTERVAL_HOUR_TO_MINUTE :
            case Types.SQL_INTERVAL_HOUR_TO_SECOND :
            case Types.SQL_INTERVAL_MINUTE :
            case Types.SQL_INTERVAL_MINUTE_TO_SECOND :
            case Types.SQL_INTERVAL_SECOND :
                long seconds = ((IntervalSecondData) a).units
                               + ((IntervalSecondData) b).units;
                long nanos = ((IntervalSecondData) a).nanos
                             + ((IntervalSecondData) b).nanos;

                return new IntervalSecondData(seconds, nanos, this, true);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "IntervalType");
        }
    }
