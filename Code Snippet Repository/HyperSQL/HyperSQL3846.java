    synchronized TimeData getCurrentTime(boolean withZone) {

        resetCurrentTimestamp();

        if (withZone) {
            if (currentTime == null) {
                int seconds =
                    (int) (HsqlDateTime.getNormalisedTime(
                        getCalendarGMT(), currentMillis)) / 1000;
                int nanos = (int) (currentMillis % 1000) * 1000000;

                currentTime = new TimeData(seconds, nanos, getZoneSeconds());
            }

            return currentTime;
        } else {
            if (localTime == null) {
                int seconds =
                    (int) (HsqlDateTime.getNormalisedTime(
                        getCalendarGMT(),
                        currentMillis + getZoneSeconds() * 1000L)) / 1000;
                int nanos = (int) (currentMillis % 1000) * 1000000;

                localTime = new TimeData(seconds, nanos, 0);
            }

            return localTime;
        }
    }
