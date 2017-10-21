    public synchronized void setDate(int parameterIndex, Date x,
                                     Calendar cal) throws SQLException {

        checkSetParameterIndex(parameterIndex);

        int i = parameterIndex - 1;

        if (x == null) {
            parameterValues[i] = null;
            parameterSet[i]    = Boolean.TRUE;

            return;
        }

        Type outType = parameterTypes[i];
        Calendar calendar = cal == null ? session.getCalendar()
                : cal;

        long millis = HsqlDateTime.convertMillisFromCalendar(
                session.getCalendarGMT(), calendar, x.getTime());

        millis = HsqlDateTime.getNormalisedDate(session.getCalendarGMT(),
                millis);

        switch (outType.typeCode) {

            case Types.SQL_DATE :
            case Types.SQL_TIMESTAMP :
                parameterValues[i] = new TimestampData(millis / 1000);

                break;
            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                int zoneOffset = HsqlDateTime.getZoneMillis(calendar, millis);

                parameterValues[i] = new TimestampData(millis / 1000, 0,
                        zoneOffset / 1000);

                break;
            default :
                throw JDBCUtil.sqlException(ErrorCode.X_42561);
        }
        parameterSet[i] = Boolean.TRUE;
    }
