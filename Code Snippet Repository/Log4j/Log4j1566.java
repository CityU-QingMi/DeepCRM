    public static UUID getTimeBasedUuid() {

        final long time = ((System.currentTimeMillis() * HUNDRED_NANOS_PER_MILLI) +
            NUM_100NS_INTERVALS_SINCE_UUID_EPOCH) + (COUNT.incrementAndGet() % HUNDRED_NANOS_PER_MILLI);
        final long timeLow = (time & LOW_MASK) << SHIFT_4;
        final long timeMid = (time & MID_MASK) >> SHIFT_2;
        final long timeHi = (time & HIGH_MASK) >> SHIFT_6;
        final long most = timeLow | timeMid | TYPE1 | timeHi;
        return new UUID(most, LEAST);
    }
