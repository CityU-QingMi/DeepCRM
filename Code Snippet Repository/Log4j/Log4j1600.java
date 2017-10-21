    private void updateMidnightMillis(final long now) {
        if (now >= midnightTomorrow || now < midnightToday) {
            synchronized (this) {
                updateCachedDate(now);
                midnightToday = calcMidnightMillis(now, 0);
                midnightTomorrow = calcMidnightMillis(now, 1);

                updateDaylightSavingTime();
            }
        }
    }
