    public static boolean acceptsPrecision(int type) {

        switch (type) {

            case Types.LONGVARCHAR :
            case Types.LONGVARBINARY :
            case Types.SQL_ARRAY :
            case Types.SQL_BINARY :
            case Types.SQL_BIT :
            case Types.SQL_BIT_VARYING :
            case Types.SQL_BLOB :
            case Types.SQL_CHAR :
            case Types.SQL_NCHAR :
            case Types.SQL_CLOB :
            case Types.NCLOB :
            case Types.SQL_VARBINARY :
            case Types.SQL_VARCHAR :
            case Types.SQL_NVARCHAR :
            case Types.VARCHAR_IGNORECASE :
            case Types.SQL_DECIMAL :
            case Types.SQL_NUMERIC :
            case Types.SQL_FLOAT :
            case Types.SQL_TIME :
            case Types.SQL_TIMESTAMP :
            case Types.SQL_INTERVAL_YEAR :
            case Types.SQL_INTERVAL_YEAR_TO_MONTH :
            case Types.SQL_INTERVAL_MONTH :
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
                return true;

            default :
                return false;
        }
    }
