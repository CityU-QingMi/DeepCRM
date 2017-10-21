    protected DTIType(int typeGroup, int type, long precision, int scale) {

        super(typeGroup, type, precision, scale);

        switch (type) {

            case Types.SQL_DATE :
                startIntervalType = Types.SQL_INTERVAL_YEAR;
                endIntervalType   = Types.SQL_INTERVAL_DAY;
                break;

            case Types.SQL_TIME :
            case Types.SQL_TIME_WITH_TIME_ZONE :
                startIntervalType = Types.SQL_INTERVAL_HOUR;
                endIntervalType   = Types.SQL_INTERVAL_SECOND;
                break;

            case Types.SQL_TIMESTAMP :
            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                startIntervalType = Types.SQL_INTERVAL_YEAR;
                endIntervalType   = Types.SQL_INTERVAL_SECOND;
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "DTIType");
        }

        startPartIndex = intervalIndexMap.get(startIntervalType);
        endPartIndex   = intervalIndexMap.get(endIntervalType);
    }
