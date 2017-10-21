    public int displaySize() {

        switch (typeCode) {

            case Types.SQL_DATE :
                return 10;

            case Types.SQL_TIME :
                return 8 + (scale == 0 ? 0
                                       : scale + 1);

            case Types.SQL_TIME_WITH_TIME_ZONE :
                return 8 + (scale == 0 ? 0
                                       : scale + 1) + 6;

            case Types.SQL_TIMESTAMP :
                return 19 + (scale == 0 ? 0
                                        : scale + 1);

            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                return 19 + (scale == 0 ? 0
                                        : scale + 1) + 6;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "DateTimeType");
        }
    }
