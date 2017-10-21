    synchronized TimestampData getCurrentTimestamp(boolean withZone) {

        resetCurrentTimestamp();

        if (withZone) {
            if (currentTimestamp == null) {
                int nanos = (int) (currentMillis % 1000) * 1000000;

                currentTimestamp = new TimestampData((currentMillis / 1000),
                                                     nanos, getZoneSeconds());
            }

            return currentTimestamp;
        } else {
            if (localTimestamp == null) {
                int nanos = (int) (currentMillis % 1000) * 1000000;

                localTimestamp = new TimestampData(currentMillis / 1000
                                                   + getZoneSeconds(), nanos,
                                                       0);
            }

            return localTimestamp;
        }
    }
