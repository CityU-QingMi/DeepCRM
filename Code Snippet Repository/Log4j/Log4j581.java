    @Test
    public void debugLogger() {
        final Timer timer = new Timer("DebugLogger", LOOP_CNT * THREADS);
        final Runnable runnable = new DebugLoggerRunnable();
        final ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        timer.start();
        for (int i=0; i < THREADS; ++i) {
            pool.execute(runnable);
        }
        pool.shutdown();
        timer.stop();
        System.out.println(timer.toString());
    }
