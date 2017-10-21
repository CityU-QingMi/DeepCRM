    public int getPart(Session session, Object interval, int part) {

        long units;

        switch (part) {

            case Types.SQL_INTERVAL_YEAR :
                return ((IntervalMonthData) interval).units / 12;

            case Types.SQL_INTERVAL_MONTH :
                units = ((IntervalMonthData) interval).units;

                return part == startIntervalType ? (int) units
                                                 : (int) (units % 12);

            case Types.SQL_INTERVAL_DAY :
                return (int) (((IntervalSecondData) interval).units
                              / (24 * 60 * 60));

            case Types.SQL_INTERVAL_HOUR : {
                units = ((IntervalSecondData) interval).units / (60 * 60);

                return part == startIntervalType ? (int) units
                                                 : (int) (units % 24);
            }
            case Types.SQL_INTERVAL_MINUTE : {
                units = ((IntervalSecondData) interval).units / 60;

                return part == startIntervalType ? (int) units
                                                 : (int) (units % 60);
            }
            case Types.SQL_INTERVAL_SECOND : {
                units = ((IntervalSecondData) interval).units;

                return part == startIntervalType ? (int) units
                                                 : (int) (units % 60);
            }
            case MILLISECOND :
                return ((IntervalSecondData) interval).nanos / 1000000;

            case MICROSECOND :
                return ((IntervalSecondData) interval).nanos / 1000;

            case NANOSECOND :
                return ((IntervalSecondData) interval).nanos;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "IntervalType");
        }
    }
