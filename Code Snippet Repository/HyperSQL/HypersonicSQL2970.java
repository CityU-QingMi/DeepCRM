    protected TimeData readTime(Type type) {

        if (type.typeCode == Types.SQL_TIME) {
            long millis = readLong();

            millis = HsqlDateTime.convertMillisFromCalendar(tempCalDefault,
                    millis);
            millis = HsqlDateTime.getNormalisedTime(millis);

            return new TimeData((int) (millis / 1000), 0, 0);
        } else {
            return new TimeData(readInt(), readInt(), readInt());
        }
    }
