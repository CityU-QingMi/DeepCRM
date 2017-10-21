    public int getJDBCTypeCode() {

        switch (typeCode) {

            case Types.SQL_TIME_WITH_TIME_ZONE :
                return Types.TIME_WITH_TIMEZONE;

            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                return Types.TIMESTAMP_WITH_TIMEZONE;

            default :

                // JDBC numbers happen to be the same as SQL
                return typeCode;
        }
    }
