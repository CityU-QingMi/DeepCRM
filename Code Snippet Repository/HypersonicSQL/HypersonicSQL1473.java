    public synchronized Date getDate(int parameterIndex,
                                     Calendar cal) throws SQLException {

        TimestampData t = (TimestampData) getColumnInType(parameterIndex,
            Type.SQL_DATE);

        if (t == null) {
            return null;
        }

        long millis = t.getSeconds() * 1000;

        if (cal != null) {
            millis = HsqlDateTime.convertMillisToCalendar(cal, millis);
        }

        return new Date(millis);
    }
