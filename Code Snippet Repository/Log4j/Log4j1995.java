    public static void runLatencyTest(final IPerfTestRunner logger, final long durationMillis, final int samples,
            final double loadMessagesPerSec, final IdleStrategy idleStrategy,
            final List<Histogram> serviceTmHistograms, final List<Histogram> responseTmHistograms,
            final int threadCount) throws InterruptedException {

        final Thread[] threads = new Thread[threadCount];
        final CountDownLatch LATCH = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final Histogram serviceTmHist = new Histogram(TimeUnit.SECONDS.toNanos(10), 3);
            final Histogram responseTmHist = new Histogram(TimeUnit.SECONDS.toNanos(10), 3);
            serviceTmHistograms.add(serviceTmHist);
            responseTmHistograms.add(responseTmHist);

            threads[i] = new Thread("latencytest-" + i) {
                @Override
                public void run() {
                    LATCH.countDown();
                    try {
                        LATCH.await(); // wait until all threads are ready to go
                    } catch (final InterruptedException e) {
                        interrupt();
                        return;
                    }
                    final long endTimeMillis = System.currentTimeMillis() + durationMillis;
                    do {
                        final Pacer pacer = new Pacer(loadMessagesPerSec, idleStrategy);
                        runLatencyTest(samples, logger, serviceTmHist, responseTmHist, pacer);
                    } while (System.currentTimeMillis() < endTimeMillis);
                }
            };
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }
    }
