    private static void doTestRun(final NoGcLogger logger, final int repetitions, final int n) {
        startTime[n] = System.currentTimeMillis();
        final long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) {
            logger.log("Test message str={}, double={}, int={}, obj={}", "abc", box(i / 2.5), box(i), "XYX");
            //logger.log("Test message str={}, double={}, int={}, obj={}", "abc", (i / 2.5), (i), logger);
        }
        duration[n] = System.nanoTime() - start;
        checksums[n] = logger.appender.checksum;
        logger.appender.checksum = 0;
    }
