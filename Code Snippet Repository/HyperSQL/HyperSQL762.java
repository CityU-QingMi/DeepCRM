    public Date getDate(int columnIndex, Calendar cal) throws SQLException {

        TimestampData t = (TimestampData) getColumnInType(columnIndex,
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
