    public boolean isDateOrTimestampType() {

        switch (typeCode) {

            case Types.SQL_DATE :
            case Types.SQL_TIMESTAMP :
            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                return true;

            case Types.SQL_TIME :
            case Types.SQL_TIME_WITH_TIME_ZONE :
                return false;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "DateTimeType");
        }
    }
