    private static void doTestRun(final ClassicLogger logger, final int repetitions, final int n) {
        startTime[n] = System.currentTimeMillis();
        final long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            logger.log("Test message str={}, double={}, int={}, obj={}", "abc", i / 2.5, i, "XYX");
        }
        duration[n] = System.nanoTime() - start;
        checksums[n] = logger.appender.checksum;
        logger.appender.checksum = 0;
    }
