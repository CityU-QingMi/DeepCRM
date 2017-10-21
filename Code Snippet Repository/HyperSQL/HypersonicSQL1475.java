    public synchronized Timestamp getTimestamp(int parameterIndex,
            Calendar cal) throws SQLException {

        TimestampData t = (TimestampData) getColumnInType(parameterIndex,
            Type.SQL_TIMESTAMP);

        if (t == null) {
            return null;
        }

        long millis = t.getSeconds() * 1000;

        if (!parameterMetaData.columnTypes[--parameterIndex]
                .isDateTimeTypeWithZone()) {
            Calendar calendar = cal == null ? session.getCalendar()
                    : cal;

            if (cal != null) {
                millis = HsqlDateTime.convertMillisToCalendar(calendar,
                        millis);
            }
        }

        Timestamp ts = new Timestamp(millis);

        ts.setNanos(t.getNanos());

        return ts;
    }
