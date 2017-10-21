    static void zeroFromPart(Calendar cal, int part) {

        switch (part) {

            case Types.SQL_INTERVAL_YEAR :
                cal.set(Calendar.MONTH, 0);
            case Types.SQL_INTERVAL_MONTH :
                cal.set(Calendar.DAY_OF_MONTH, 1);
            case Types.SQL_INTERVAL_DAY :
                cal.set(Calendar.HOUR_OF_DAY, 0);
            case Types.SQL_INTERVAL_HOUR :
                cal.set(Calendar.MINUTE, 0);
            case Types.SQL_INTERVAL_MINUTE :
                cal.set(Calendar.SECOND, 0);
            case Types.SQL_INTERVAL_SECOND :
                cal.set(Calendar.MILLISECOND, 0);
            default :
        }
    }
