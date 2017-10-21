    @Test
    public void debugDisabled() {
        final Timer timer = new Timer("DebugDisabled", LOOP_CNT * THREADS);
        final Runnable runnable = new DebugDisabledRunnable();
        final ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        timer.start();
        for (int i=0; i < THREADS; ++i) {
            pool.execute(runnable);
        }
        pool.shutdown();
        timer.stop();
        System.out.println(timer.toString());
    }
