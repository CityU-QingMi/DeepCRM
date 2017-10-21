    protected TimestampData readDate(Type type) {

        long millis = readLong();

        millis = HsqlDateTime.convertMillisFromCalendar(tempCalDefault,
                millis);
        millis = HsqlDateTime.getNormalisedDate(millis);

        return new TimestampData(millis / 1000);
    }
