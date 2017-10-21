    public static TimestampData addSeconds(TimestampData source, long seconds,
                                           int nanos) {

        nanos   += source.getNanos();
        seconds += nanos / limitNanoseconds;
        nanos   %= limitNanoseconds;

        if (nanos < 0) {
            nanos += limitNanoseconds;

            seconds--;
        }

        long newSeconds = source.getSeconds() + seconds;
        TimestampData ts = new TimestampData(newSeconds, nanos,
                                             source.getZone());

        return ts;
    }
