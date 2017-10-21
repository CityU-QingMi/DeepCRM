    protected void writeTimestamp(TimestampData o, Type type) {

        if (type.typeCode == Types.SQL_TIMESTAMP) {
            long millis = o.getSeconds() * 1000L;

            millis = HsqlDateTime.convertMillisToCalendar(tempCalDefault,
                    millis);

            writeLong(millis);
            writeInt(o.getNanos());
        } else {
            writeLong(o.getSeconds());
            writeInt(o.getNanos());
            writeInt(o.getZone());
        }
    }
