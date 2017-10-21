    @Override
    public void runThroughputTest(final int lines, final Histogram histogram) {
        final long s1 = System.nanoTime();
        final Logger logger = LOGGER;
        for (int j = 0; j < lines; j++) {
            logger.info(THROUGHPUT_MSG);
        }
        final long s2 = System.nanoTime();
        final long opsPerSec = (1000L * 1000L * 1000L * lines) / (s2 - s1);
        histogram.addObservation(opsPerSec);
    }
