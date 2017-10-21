    @Test
    public void testShrink() throws Exception
    {
        final long IDLE = 1000;

        _reservedExecutor.stop();
        _reservedExecutor.setIdleTimeout(IDLE,TimeUnit.MILLISECONDS);
        _reservedExecutor.start();

        // Reserved threads are lazily started.
        assertThat(_executor._queue.size(), is(0));

        assertThat(_reservedExecutor.tryExecute(NOOP),is(false));
        _executor.execute();
        waitForNoPending();

        CountDownLatch latch = new CountDownLatch(1);
        Runnable waitForLatch = ()->{try {latch.await();} catch(Exception e){}};
        assertThat(_reservedExecutor.tryExecute(waitForLatch),is(true));
        _executor.execute();

        assertThat(_reservedExecutor.tryExecute(NOOP),is(false));
        _executor.execute();
        waitForNoPending();

        latch.countDown();
        waitForAvailable(2);

        // Check that regular moderate activity keeps the pool a moderate size
        TimeUnit.MILLISECONDS.sleep(IDLE/2);
        assertThat(_reservedExecutor.tryExecute(NOOP),is(true));
        waitForAvailable(2);
        TimeUnit.MILLISECONDS.sleep(IDLE/2);
        assertThat(_reservedExecutor.tryExecute(NOOP),is(true));
        waitForAvailable(1);
        TimeUnit.MILLISECONDS.sleep(IDLE/2);
        assertThat(_reservedExecutor.tryExecute(NOOP),is(true));
        waitForAvailable(1);
        TimeUnit.MILLISECONDS.sleep(IDLE/2);
        assertThat(_reservedExecutor.tryExecute(NOOP),is(true));
        waitForAvailable(1);
        TimeUnit.MILLISECONDS.sleep(IDLE/2);
        assertThat(_reservedExecutor.tryExecute(NOOP),is(true));
        waitForAvailable(1);

        // check fully idle goes to zero
        TimeUnit.MILLISECONDS.sleep(IDLE);
        assertThat(_reservedExecutor.getAvailable(),is(0));

    }
