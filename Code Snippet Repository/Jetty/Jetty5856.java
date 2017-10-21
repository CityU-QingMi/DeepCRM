    @Test
    public void testExecuted() throws Exception
    {
        assertThat(_executor._queue.size(), is(0));

        for (int i = 0; i < SIZE; i++)
            _reservedExecutor.tryExecute(NOOP);
        assertThat(_executor._queue.size(), is(SIZE));

        for (int i = 0; i < SIZE; i++)
            _executor.execute();
        assertThat(_executor._queue.size(), is(0));

        waitForAllAvailable();

        Task[] tasks = new Task[SIZE];
        for (int i = 0; i < SIZE; i++)
        {
            tasks[i] = new Task();
            assertThat(_reservedExecutor.tryExecute(tasks[i]), is(true));
        }

        for (int i = 0; i < SIZE; i++)
            tasks[i]._ran.await(10, TimeUnit.SECONDS);

        assertThat(_executor._queue.size(), is(1));

        Task extra = new Task();
        assertThat(_reservedExecutor.tryExecute(extra), is(false));
        assertThat(_executor._queue.size(), is(2));

        Thread.sleep(500);
        assertThat(extra._ran.getCount(), is(1L));

        for (int i = 0; i < SIZE; i++)
            tasks[i]._complete.countDown();

        waitForAllAvailable();
    }
