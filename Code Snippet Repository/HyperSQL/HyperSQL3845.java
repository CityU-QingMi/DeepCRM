    public synchronized TimestampData getCurrentDate() {

        resetCurrentTimestamp();

        if (currentDate == null) {
            currentDate = (TimestampData) Type.SQL_DATE.getValue(currentMillis
                    / 1000, 0, getZoneSeconds());
        }

        return currentDate;
    }
