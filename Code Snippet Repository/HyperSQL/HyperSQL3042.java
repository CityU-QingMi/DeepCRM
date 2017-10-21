    public boolean isIntervalYearMonthType() {

        switch (typeCode) {

            case Types.SQL_INTERVAL_YEAR :
            case Types.SQL_INTERVAL_YEAR_TO_MONTH :
            case Types.SQL_INTERVAL_MONTH :
                return true;

            default :
                return false;
        }
    }
