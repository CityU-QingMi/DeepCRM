    protected TimeData readTime(Type type) {

        readField();

        if (value == null) {
            return null;
        }

        if (version18) {
            java.sql.Time dateTime = java.sql.Time.valueOf((String) value);
            long millis =
                HsqlDateTime.convertMillisFromCalendar(tempCalDefault,
                    dateTime.getTime());

            millis = HsqlDateTime.getNormalisedTime(millis);

            return new TimeData((int) millis / 1000, 0, 0);
        }

        return scanner.newTime((String) value);
    }
