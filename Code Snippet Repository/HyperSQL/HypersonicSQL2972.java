    protected TimestampData readTimestamp(Type type) {

        if (type.typeCode == Types.SQL_TIMESTAMP) {
            long millis = readLong();
            int  nanos  = readInt();

            millis = HsqlDateTime.convertMillisFromCalendar(tempCalDefault,
                    millis);

            return new TimestampData(millis / 1000, nanos);
        } else {
            return new TimestampData(readLong(), readInt(), readInt());
        }
    }
