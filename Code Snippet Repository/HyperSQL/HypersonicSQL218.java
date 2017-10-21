    static int getSQLTypeForToken(String string) {

        int type = -1;

        if ("YEAR_MONTH".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_YEAR_TO_MONTH;
        } else if ("DAY_HOUR".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_DAY_TO_HOUR;
        } else if ("DAY_MINUTE".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_DAY_TO_MINUTE;
        } else if ("DAY_SECOND".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_DAY_TO_SECOND;
        } else if ("DAY_MICROSECOND".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_DAY_TO_SECOND;
        } else if ("HOUR_MINUTE".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_HOUR_TO_MINUTE;
        } else if ("HOUR_SECOND".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_HOUR_TO_SECOND;
        } else if ("HOUR_MICROSECOND".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_HOUR_TO_SECOND;
        } else if ("MINUTE_SECOND".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_MINUTE_TO_SECOND;
        } else if ("MINUTE_MICROSECOND".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_MINUTE_TO_SECOND;
        } else if ("SECOND_MICROSECOND".equalsIgnoreCase(string)) {
            type = Types.SQL_INTERVAL_SECOND;
        }

        return type;
    }
