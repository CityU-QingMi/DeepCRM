    public static String getQualifier(int type) {

        switch (type) {

            case Types.SQL_INTERVAL_YEAR :
                return Tokens.T_YEAR;

            case Types.SQL_INTERVAL_YEAR_TO_MONTH :
                return "YEAR TO MONTH";

            case Types.SQL_INTERVAL_MONTH :
                return Tokens.T_MONTH;

            case Types.SQL_INTERVAL_DAY :
                return Tokens.T_DAY;

            case Types.SQL_INTERVAL_DAY_TO_HOUR :
                return "DAY TO HOUR";

            case Types.SQL_INTERVAL_DAY_TO_MINUTE :
                return "DAY TO MINUTE";

            case Types.SQL_INTERVAL_DAY_TO_SECOND :
                return "DAY TO SECOND";

            case Types.SQL_INTERVAL_HOUR :
                return Tokens.T_HOUR;

            case Types.SQL_INTERVAL_HOUR_TO_MINUTE :
                return "HOUR TO MINUTE";

            case Types.SQL_INTERVAL_HOUR_TO_SECOND :
                return "HOUR TO SECOND";

            case Types.SQL_INTERVAL_MINUTE :
                return Tokens.T_MINUTE;

            case Types.SQL_INTERVAL_MINUTE_TO_SECOND :
                return "MINUTE TO SECOND";

            case Types.SQL_INTERVAL_SECOND :
                return Tokens.T_SECOND;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "IntervalType");
        }
    }
