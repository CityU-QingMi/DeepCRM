    protected TimestampData readTimestamp(Type type) {

        readField();

        if (value == null) {
            return null;
        }

        if (version18) {
            java.sql.Timestamp dateTime =
                java.sql.Timestamp.valueOf((String) value);
            long millis =
                HsqlDateTime.convertMillisFromCalendar(tempCalDefault,
                    dateTime.getTime());
            int nanos = dateTime.getNanos();

            nanos = DateTimeType.normaliseFraction(nanos, type.scale);

            return new TimestampData(millis / 1000, nanos, 0);
        }

        return scanner.newTimestamp((String) value);
    }
