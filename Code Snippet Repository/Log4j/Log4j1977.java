    public void runTestAndPrintResult(final IPerfTestRunner runner, final String name, final int threadCount,
            final String resultFile) throws Exception {
        final Histogram warmupHist = createHistogram();

        // ThreadContext.put("aKey", "mdcVal");
        println("Warming up the JVM...");
        final long t1 = System.nanoTime();

        // warmup at least 10 seconds
        final int LINES = 50000;
        int iterations = 0;
        final long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(10);
        do {
            runTest(runner, LINES, null, warmupHist, 1);
            iterations++;
        } while (System.nanoTime() - stop < 0);

        printf("Warmup complete in %.1f seconds (%d iterations)%n", (System.nanoTime() - t1)
                / (1000.0 * 1000.0 * 1000.0), iterations);
        println("Waiting 10 seconds for buffers to drain warmup data...");

        Thread.sleep(3000);
        //forceRemap(LINES, iterations, runner);
        Thread.sleep(7000);

        println("Starting the main test...");
        runSingleThreadedTest(runner, LINES, name, resultFile);
        Thread.sleep(1000);
    }
