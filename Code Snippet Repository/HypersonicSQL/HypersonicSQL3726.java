    public static int getCombinedIntervalType(int startType, int endType) {

        if (startType == endType) {
            return startType;
        }

        switch (startType) {

            case Types.SQL_INTERVAL_YEAR :
                if (endType == Types.SQL_INTERVAL_MONTH) {
                    return Types.SQL_INTERVAL_YEAR_TO_MONTH;
                }
                break;

            case Types.SQL_INTERVAL_DAY :
                switch (endType) {

                    case Types.SQL_INTERVAL_HOUR :
                        return Types.SQL_INTERVAL_DAY_TO_HOUR;

                    case Types.SQL_INTERVAL_MINUTE :
                        return Types.SQL_INTERVAL_DAY_TO_MINUTE;

                    case Types.SQL_INTERVAL_SECOND :
                        return Types.SQL_INTERVAL_DAY_TO_SECOND;
                }
                break;

            case Types.SQL_INTERVAL_HOUR :
                switch (endType) {

                    case Types.SQL_INTERVAL_MINUTE :
                        return Types.SQL_INTERVAL_HOUR_TO_MINUTE;

                    case Types.SQL_INTERVAL_SECOND :
                        return Types.SQL_INTERVAL_HOUR_TO_SECOND;
                }
                break;

            case Types.SQL_INTERVAL_MINUTE :
                if (endType == Types.SQL_INTERVAL_SECOND) {
                    return Types.SQL_INTERVAL_MINUTE_TO_SECOND;
                }
                break;

            default :
        }

        throw Error.runtimeError(ErrorCode.U_S0500, "IntervalType");
    }
