    protected TimestampData readDate(Type type) {

        readField();

        if (value == null) {
            return null;
        }

        if (version18) {
            java.sql.Date dateTime = java.sql.Date.valueOf((String) value);
            long millis =
                HsqlDateTime.convertMillisFromCalendar(tempCalDefault,
                    dateTime.getTime());

            millis = HsqlDateTime.getNormalisedDate(millis);

            return new TimestampData(millis / 1000);
        }

        return scanner.newDate((String) value);
    }
