    protected void writeTime(TimeData o, Type type) {

        if (type.typeCode == Types.SQL_TIME) {
            long millis = o.getSeconds() * 1000L;

            millis = HsqlDateTime.convertMillisToCalendar(tempCalDefault,
                    millis);

            writeLong(millis);
        } else {
            writeInt(o.getSeconds());
            writeInt(o.getNanos());
            writeInt(o.getZone());
        }
    }
