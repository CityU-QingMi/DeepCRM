    public static DateTimeType getDateTimeType(int type, int scale) {

        if (scale > DTIType.maxFractionPrecision) {
            throw Error.error(ErrorCode.X_42592);
        }

        switch (type) {

            case Types.SQL_DATE :
                return SQL_DATE;

            case Types.SQL_TIME :
                if (scale == DTIType.defaultTimeFractionPrecision) {
                    return SQL_TIME;
                }

                return new DateTimeType(Types.SQL_TIME, type, scale);

            case Types.SQL_TIME_WITH_TIME_ZONE :
                if (scale == DTIType.defaultTimeFractionPrecision) {
                    return SQL_TIME_WITH_TIME_ZONE;
                }

                return new DateTimeType(Types.SQL_TIME, type, scale);

            case Types.SQL_TIMESTAMP :
                if (scale == DTIType.defaultTimestampFractionPrecision) {
                    return SQL_TIMESTAMP;
                }

                if (scale == 0) {
                    return SQL_TIMESTAMP_NO_FRACTION;
                }

                return new DateTimeType(Types.SQL_TIMESTAMP, type, scale);

            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                if (scale == DTIType.defaultTimestampFractionPrecision) {
                    return SQL_TIMESTAMP_WITH_TIME_ZONE;
                }

                return new DateTimeType(Types.SQL_TIMESTAMP, type, scale);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "DateTimeType");
        }
    }
