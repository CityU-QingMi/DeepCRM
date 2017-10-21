    public long convertToLongEndUnits(Object interval) {

        switch (endIntervalType) {

            case Types.SQL_INTERVAL_YEAR :
            case Types.SQL_INTERVAL_MONTH :
                long months = ((IntervalMonthData) interval).units;

                return (months / DTIType.yearToSecondFactors[endPartIndex]);

            case Types.SQL_INTERVAL_DAY :
            case Types.SQL_INTERVAL_HOUR :
            case Types.SQL_INTERVAL_MINUTE :
            case Types.SQL_INTERVAL_SECOND : {
                long seconds = ((IntervalSecondData) interval).units;

                return (seconds / DTIType.yearToSecondFactors[endPartIndex]);
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "IntervalType");
        }
    }
