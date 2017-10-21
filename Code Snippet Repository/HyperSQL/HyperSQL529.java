    public synchronized Time getTime(int parameterIndex,
                                     Calendar cal) throws SQLException {

        TimeData t = (TimeData) getColumnInType(parameterIndex, Type.SQL_TIME);

        if (t == null) {
            return null;
        }

        long millis = DateTimeType.normaliseTime(t.getSeconds()) * 1000L;

        if (!parameterMetaData.columnTypes[--parameterIndex]
                .isDateTimeTypeWithZone()) {
            Calendar calendar = cal == null ? session.getCalendar()
                    : cal;

            millis = HsqlDateTime.convertMillisToCalendar(calendar, millis);
            millis = HsqlDateTime.getNormalisedTime(millis);
        }

        return new Time(millis);
    }
