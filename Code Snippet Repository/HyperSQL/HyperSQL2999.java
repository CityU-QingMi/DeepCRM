    public Object convertToTypeLimits(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (scale == maxFractionPrecision) {
            return a;
        }

        switch (typeCode) {

            case Types.SQL_DATE :
                return a;

            case Types.SQL_TIME_WITH_TIME_ZONE :
            case Types.SQL_TIME : {
                TimeData ti       = (TimeData) a;
                int      nanos    = ti.getNanos();
                int      newNanos = scaleNanos(nanos);

                if (newNanos == nanos) {
                    return ti;
                }

                return new TimeData(ti.getSeconds(), newNanos, ti.getZone());
            }
            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
            case Types.SQL_TIMESTAMP : {
                TimestampData ts       = (TimestampData) a;
                int           nanos    = ts.getNanos();
                int           newNanos = scaleNanos(nanos);

                if (newNanos == nanos) {
                    return ts;
                }

                return new TimestampData(ts.getSeconds(), newNanos,
                                         ts.getZone());
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "DateTimeType");
        }
    }
