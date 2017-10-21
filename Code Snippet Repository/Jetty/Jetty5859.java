    protected void waitForAvailable(int size) throws InterruptedException
    {
        long started = System.nanoTime();
        while (_reservedExecutor.getAvailable() < size)
        {
            long elapsed = System.nanoTime() - started;
            if (elapsed > TimeUnit.SECONDS.toNanos(10))
                Assert.fail();
            Thread.sleep(10);
        }
        assertThat(_reservedExecutor.getAvailable(), is(size));
    }
