    public int getSqlDateTimeSub() {

        switch (typeCode) {

            case Types.SQL_DATE :
                return 1;

            case Types.SQL_TIME :
                return 2;

            case Types.SQL_TIMESTAMP :
                return 3;

            default :
                return 0;
        }
    }
