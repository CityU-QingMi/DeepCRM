    protected Date getTimeBefore(final Date targetDate) {
        final Calendar cl = Calendar.getInstance(getTimeZone());

        // to match this
        Date start = targetDate;
        final long minIncrement = findMinIncrement();
        Date prevFireTime;
        do {
            final Date prevCheckDate = new Date(start.getTime() - minIncrement);
            prevFireTime = getTimeAfter(prevCheckDate);
            if (prevFireTime == null || prevFireTime.before(MIN_DATE)) {
                return null;
            }
            start = prevCheckDate;
        } while (prevFireTime.compareTo(targetDate) >= 0);
        return prevFireTime;
    }
