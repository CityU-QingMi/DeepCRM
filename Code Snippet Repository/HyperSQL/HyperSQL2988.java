    public static TimeData addSeconds(TimeData source, long seconds,
                                      int nanos) {

        nanos   += source.getNanos();
        seconds += nanos / limitNanoseconds;
        nanos   %= limitNanoseconds;

        if (nanos < 0) {
            nanos += DTIType.limitNanoseconds;

            seconds--;
        }

        seconds += source.getSeconds();
        seconds %= (24 * 60 * 60);

        TimeData ti = new TimeData((int) seconds, nanos, source.getZone());

        return ti;
    }
