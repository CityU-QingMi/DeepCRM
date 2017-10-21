    private IntervalType(int typeGroup, int type, long precision, int scale,
                         int startIntervalType, int endIntervalType,
                         boolean defaultPrecision) {

        super(typeGroup, type, precision, scale, startIntervalType,
              endIntervalType);

        if (endIntervalType != Types.SQL_INTERVAL_SECOND && scale != 0) {
            throw Error.error(ErrorCode.X_22006);
        }

        switch (startIntervalType) {

            case Types.SQL_INTERVAL_YEAR :
            case Types.SQL_INTERVAL_MONTH :
                isYearMonth = true;
                break;

            default :
                isYearMonth = false;
                break;
        }

        this.defaultPrecision = defaultPrecision;
    }
