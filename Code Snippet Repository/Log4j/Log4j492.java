    @Test
    public synchronized void testGetLoggersInContextSynch() throws Exception {
        final TestLoggerAdapter adapter = new TestLoggerAdapter();

        final int num = 500;

        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch doneSignal = new CountDownLatch(num);

        final RunnableThreadTest[] instances = new RunnableThreadTest[num];
        LoggerContext lastUsedContext = null;
        for (int i = 0; i < num; i++) {
            if (i % 2 == 0) {
                //every other time create a new context
                lastUsedContext = new SimpleLoggerContext();
            }
            final RunnableThreadTest runnable = new RunnableThreadTest(i, adapter, lastUsedContext, startSignal, doneSignal);
            final Thread thread = new Thread(runnable);
            thread.start();
            instances[i] = runnable;
        }

        startSignal.countDown();
        doneSignal.await();

        for (int i = 0; i < num; i = i + 2) {
            //maps for the same context should be the same instance
            final Map<String, Logger> resultMap1 = instances[i].getResultMap();
            final Map<String, Logger> resultMap2 = instances[i + 1].getResultMap();
            assertSame("not the same map for instances" + i + " and " + (i + 1) + ":", resultMap1, resultMap2);
            assertEquals(2, resultMap1.size());
        }
    }
