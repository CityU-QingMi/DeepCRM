    private void testMultipleLockingAppenderThreads(final boolean lock, final int threadCount)
            throws InterruptedException, Exception {
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        final Exception[] exceptionRef = new Exception[1];
        final int logEventCount = 100;
        final Runnable runnable = new FileWriterRunnable(lock, logEventCount, exceptionRef);
        for (int i = 0; i < threadCount; ++i) {
            threadPool.execute(runnable);
        }
        threadPool.shutdown();
        Assert.assertTrue("The thread pool has not shutdown: " + threadPool,
                threadPool.awaitTermination(10, TimeUnit.SECONDS));
        if (exceptionRef[0] != null) {
            throw exceptionRef[0];
        }
        verifyFile(threadCount * logEventCount);
    }
