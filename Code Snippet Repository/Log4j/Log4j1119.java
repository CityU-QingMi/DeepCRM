    void initMidnight(final long now) {
        final Calendar calendar = Calendar.getInstance(timezone);
        calendar.setTimeInMillis(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        midnightToday = calendar.getTimeInMillis();

        calendar.add(Calendar.DATE, 1);
        midnightTomorrow = calendar.getTimeInMillis();
    }
