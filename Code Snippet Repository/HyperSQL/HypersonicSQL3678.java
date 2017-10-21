    public Class getJDBCClass() {

        switch (typeCode) {

            case Types.SQL_DATE :
                return java.sql.Date.class;

            case Types.SQL_TIME :
                return java.sql.Time.class;

            case Types.SQL_TIMESTAMP :
                return java.sql.Timestamp.class;


//#ifdef JAVA8
/**/
/**/
/**/
/**/
/**/
/**/
/**/
//#else
            case Types.SQL_TIME_WITH_TIME_ZONE :
                return java.sql.Time.class;

            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                return java.sql.Timestamp.class;
//#endif JAVA8

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "DateTimeType");
        }
    }
