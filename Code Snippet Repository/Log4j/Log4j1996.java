    private static void runLatencyTest(final int samples, final IPerfTestRunner logger, final Histogram serviceTmHist,
            final Histogram responseTmHist, final Pacer pacer) {

        for (int i = 0; i < samples; i++) {
            final long expectedStartTimeNanos = pacer.expectedNextOperationNanoTime();
            pacer.acquire(1);
            final long actualStartTime = System.nanoTime();
            logger.log(LATENCY_MSG);
            final long doneTime = System.nanoTime();
            serviceTmHist.recordValue(doneTime - actualStartTime);
            responseTmHist.recordValue(doneTime - expectedStartTimeNanos);
        }
    }
