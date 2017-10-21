    public Time getTime(int columnIndex, Calendar cal) throws SQLException {

        TimeData t = (TimeData) getColumnInType(columnIndex, Type.SQL_TIME);

        if (t == null) {
            return null;
        }

        long millis = DateTimeType.normaliseTime(t.getSeconds()) * 1000L;

        if (!resultMetaData.columnTypes[--columnIndex]
                .isDateTimeTypeWithZone()) {
            Calendar calendar = cal == null ? session.getCalendar()
                    : cal;

            millis = HsqlDateTime.convertMillisToCalendar(calendar, millis);
            millis = HsqlDateTime.getNormalisedTime(millis);
        }

        return new Time(millis);
    }
