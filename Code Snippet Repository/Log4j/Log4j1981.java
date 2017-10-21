    public void runTest(final IPerfTestRunner runner, final int lines, final String finalMessage,
            final Histogram histogram, final int threadCount) {
        if (throughput) {
            runner.runThroughputTest(lines, histogram);
        } else {
            final long nanoTimeCost = calcNanoTimeCost();
            runner.runLatencyTest(lines, histogram, nanoTimeCost, threadCount);
        }
        if (finalMessage != null) {
            runner.log(finalMessage);
        }
    }
