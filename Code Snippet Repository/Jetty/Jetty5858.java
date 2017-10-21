    protected void waitForNoPending() throws InterruptedException
    {
        long started = System.nanoTime();
        while (_reservedExecutor.getPending() > 0)
        {
            long elapsed = System.nanoTime() - started;
            if (elapsed > TimeUnit.SECONDS.toNanos(10))
                Assert.fail("pending="+_reservedExecutor.getPending());
            Thread.sleep(10);
        }
        assertThat(_reservedExecutor.getPending(), is(0));
    }
