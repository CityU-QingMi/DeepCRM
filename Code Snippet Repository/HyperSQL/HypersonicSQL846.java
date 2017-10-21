    synchronized TimestampData getSystemTimestamp(boolean withZone) {

        long     millis  = System.currentTimeMillis();
        long     seconds = millis / 1000;
        int      nanos   = (int) (millis % 1000) * 1000000;
        TimeZone zone    = TimeZone.getDefault();
        int      offset  = zone.getOffset(millis) / 1000;

        if (!withZone) {
            seconds += offset;
            offset  = 0;
        }

        return new TimestampData(seconds, nanos, offset);
    }
