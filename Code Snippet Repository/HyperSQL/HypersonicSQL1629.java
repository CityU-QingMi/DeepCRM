    public synchronized void setTime(int parameterIndex, Time x,
                                     Calendar cal) throws SQLException {

        checkSetParameterIndex(parameterIndex);

        int i = parameterIndex - 1;

        if (x == null) {
            parameterValues[i] = null;
            parameterSet[i]    = Boolean.TRUE;

            return;
        }

        Type     outType    = parameterTypes[i];
        long     millis     = x.getTime();
        int      zoneOffset = 0;
        Calendar calendar   = cal == null ? session.getCalendar()
                : cal;

        millis = HsqlDateTime.convertMillisFromCalendar(
                session.getCalendarGMT(), calendar, millis);
        millis = HsqlDateTime.convertToNormalisedTime(millis,
                session.getCalendarGMT());

        switch (outType.typeCode) {

            case Types.SQL_TIME :
                break;
            case Types.SQL_TIME_WITH_TIME_ZONE :
                zoneOffset = HsqlDateTime.getZoneMillis(calendar, millis);

                break;
            default :
                throw JDBCUtil.sqlException(ErrorCode.X_42561);
        }
        parameterValues[i] = new TimeData((int) (millis / 1000), 0,
                zoneOffset / 1000);
        parameterSet[i] = Boolean.TRUE;
    }
