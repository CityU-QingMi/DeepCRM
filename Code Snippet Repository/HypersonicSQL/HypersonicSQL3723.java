    public static int getStartIntervalType(int type) {

        int startType;

        switch (type) {

            case Types.SQL_INTERVAL_YEAR :
            case Types.SQL_INTERVAL_YEAR_TO_MONTH :
                startType = Types.SQL_INTERVAL_YEAR;
                break;

            case Types.SQL_INTERVAL_MONTH :
                startType = Types.SQL_INTERVAL_MONTH;
                break;

            case Types.SQL_INTERVAL_DAY :
            case Types.SQL_INTERVAL_DAY_TO_HOUR :
            case Types.SQL_INTERVAL_DAY_TO_MINUTE :
            case Types.SQL_INTERVAL_DAY_TO_SECOND :
                startType = Types.SQL_INTERVAL_DAY;
                break;

            case Types.SQL_INTERVAL_HOUR :
            case Types.SQL_INTERVAL_HOUR_TO_MINUTE :
            case Types.SQL_INTERVAL_HOUR_TO_SECOND :
                startType = Types.SQL_INTERVAL_HOUR;
                break;

            case Types.SQL_INTERVAL_MINUTE :
            case Types.SQL_INTERVAL_MINUTE_TO_SECOND :
                startType = Types.SQL_INTERVAL_MINUTE;
                break;

            case Types.SQL_INTERVAL_SECOND :
                startType = Types.SQL_INTERVAL_SECOND;
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "IntervalType");
        }

        return startType;
    }
