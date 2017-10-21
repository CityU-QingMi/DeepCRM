    public double convertToDoubleStartUnits(Object interval) {

        switch (startIntervalType) {

            case Types.SQL_INTERVAL_YEAR :
            case Types.SQL_INTERVAL_MONTH :
                double months = ((IntervalMonthData) interval).units;

                return (months / DTIType.yearToSecondFactors[startPartIndex]);

            case Types.SQL_INTERVAL_DAY :
            case Types.SQL_INTERVAL_HOUR :
            case Types.SQL_INTERVAL_MINUTE :
            case Types.SQL_INTERVAL_SECOND : {
                double seconds = ((IntervalSecondData) interval).units;

                return (seconds / DTIType.yearToSecondFactors[startPartIndex]);
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "IntervalType");
        }
    }
