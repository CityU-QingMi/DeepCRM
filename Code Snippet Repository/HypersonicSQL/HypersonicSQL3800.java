    public TimeData(int seconds, int nanos, int zoneSeconds) {

        while (seconds < 0) {
            seconds += 24 * 60 * 60;
        }

        if (seconds > 24 * 60 * 60) {
            seconds %= 24 * 60 * 60;
        }

        this.zone    = zoneSeconds;
        this.seconds = seconds;
        this.nanos   = nanos;
    }
