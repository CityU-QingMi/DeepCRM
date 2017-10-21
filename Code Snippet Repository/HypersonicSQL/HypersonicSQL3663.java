    public Object subtract(Session session, Object a, Object b,
                           Type otherType) {

        if (a == null || b == null) {
            return null;
        }

        if (otherType.isNumberType()) {
            if (typeCode == Types.SQL_DATE) {
                b = ((NumberType) otherType).floor(b);
            }

            b = Type.SQL_INTERVAL_SECOND_MAX_PRECISION.multiply(
                IntervalSecondData.oneDay, b);
        }

        switch (typeCode) {

            case Types.SQL_TIME_WITH_TIME_ZONE :
            case Types.SQL_TIME :
                if (b instanceof IntervalMonthData) {
                    throw Error.runtimeError(ErrorCode.U_S0500,
                                             "DateTimeType");
                } else if (b instanceof IntervalSecondData) {
                    return addSeconds((TimeData) a,
                                      -((IntervalSecondData) b).units,
                                      -((IntervalSecondData) b).nanos);
                }
                break;

            case Types.SQL_DATE :
            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
            case Types.SQL_TIMESTAMP :
                if (b instanceof IntervalMonthData) {
                    return addMonths(session, (TimestampData) a,
                                     -((IntervalMonthData) b).units);
                } else if (b instanceof IntervalSecondData) {
                    return addSeconds((TimestampData) a,
                                      -((IntervalSecondData) b).units,
                                      -((IntervalSecondData) b).nanos);
                }
                break;

            default :
        }

        throw Error.runtimeError(ErrorCode.U_S0500, "DateTimeType");
    }
