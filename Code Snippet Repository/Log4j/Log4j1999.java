    @Override
    public void runLatencyTest(final int samples, final Histogram histogram,
            final long nanoTimeCost, final int threadCount) {
        final Logger logger = LOGGER;
        for (int i = 0; i < samples; i++) {
            final long s1 = System.nanoTime();
            logger.info(LATENCY_MSG);
            final long s2 = System.nanoTime();
            final long value = s2 - s1 - nanoTimeCost;
            if (value > 0) {
                histogram.addObservation(value);
            }
            // wait 1 microsec
            final long PAUSE_NANOS = 10000 * threadCount;
            final long pauseStart = System.nanoTime();
            while (PAUSE_NANOS > (System.nanoTime() - pauseStart)) {
                // busy spin
            }
        }
    }
