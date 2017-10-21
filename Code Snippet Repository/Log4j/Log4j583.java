    @Test
    public void testDeadlock() throws Exception {
        final ExecutorService pool = Executors.newFixedThreadPool(THREADS * 2);
        final State state = new State();
        for (int count=0; count < THREADS; ++count) {
            pool.execute(new LoggingRunnable(state));
            pool.execute(new StateSettingRunnable(state));
        }
        Thread.sleep(250);
        pool.shutdown();
        System.out.println("Counter = " + counter);
    }
