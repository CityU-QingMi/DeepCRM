    @Override
    public void runTestAndPrintResult(final IPerfTestRunner runner,
            final String name, final int threadCount, final String resultFile)
            throws Exception {

        // ThreadContext.put("aKey", "mdcVal");
        PerfTest.println("Warming up the JVM...");
        final long t1 = System.nanoTime();

        // warmup at least 2 rounds and at most 1 minute
        final Histogram warmupHist = PerfTest.createHistogram();
        final long stop = System.nanoTime() + TimeUnit.MINUTES.toNanos(1);
        final Runnable run1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    final int LINES = PerfTest.throughput ? 50000 : 200000;
                    runTest(runner, LINES, null, warmupHist, 2);
                    if (i > 0 && System.nanoTime() - stop >= 0) {
                        return;
                    }
                }
            }
        };
        final Thread thread1 = new Thread(run1);
        final Thread thread2 = new Thread(run1);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        PerfTest.printf("Warmup complete in %.1f seconds%n",
                (System.nanoTime() - t1) / (1000.0 * 1000.0 * 1000.0));
        PerfTest.println("Waiting 10 seconds for buffers to drain warmup data...");
        Thread.sleep(10000);
        new File("perftest.log").delete();
        new File("perftest.log").createNewFile();

        PerfTest.println("Starting the main test...");
        PerfTest.throughput = false;
        multiThreadedTestRun(runner, name, threadCount, resultFile);

        Thread.sleep(1000);
        PerfTest.throughput = true;
        multiThreadedTestRun(runner, name, threadCount, resultFile);
    }
