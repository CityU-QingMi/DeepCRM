    private void multiThreadedTestRun(final IPerfTestRunner runner,
            final String name, final int threadCount, final String resultFile)
            throws Exception {

        final Histogram[] histograms = new Histogram[threadCount];
        for (int i = 0; i < histograms.length; i++) {
            histograms[i] = PerfTest.createHistogram();
        }
        final int LINES = 256 * 1024;

        final Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threads.length; i++) {
            final Histogram histogram = histograms[i];
            threads[i] = new Thread() {
                @Override
                public void run() {
//                    int latencyCount = threadCount >= 16 ? 1000000 : 5000000;
                    final int latencyCount = 5000000;
                    final int count = PerfTest.throughput ? LINES / threadCount
                            : latencyCount;
                    runTest(runner, count, "end", histogram, threadCount);
                }
            };
        }
        for (final Thread thread : threads) {
            thread.start();
        }
        for (final Thread thread : threads) {
            thread.join();
        }

        for (final Histogram histogram : histograms) {
            PerfTest.reportResult(resultFile, name, histogram);
        }
}
