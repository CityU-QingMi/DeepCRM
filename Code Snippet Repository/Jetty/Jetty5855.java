    @Test
    public void testPending() throws Exception
    {
        assertThat(_executor._queue.size(), is(0));

        for (int i = 0; i < SIZE; i++)
            _reservedExecutor.tryExecute(NOOP);
        assertThat(_executor._queue.size(), is(SIZE));

        for (int i = 0; i < SIZE; i++)
            _executor.execute();
        assertThat(_executor._queue.size(), is(0));

        waitForAllAvailable();

        for (int i = 0; i < SIZE; i++)
            assertThat(_reservedExecutor.tryExecute(new Task()), is(true));
        assertThat(_executor._queue.size(), is(1));
        assertThat(_reservedExecutor.getAvailable(), is(0));

        for (int i = 0; i < SIZE; i++)
            assertThat(_reservedExecutor.tryExecute(NOOP), is(false));
        assertThat(_executor._queue.size(), is(SIZE));
        assertThat(_reservedExecutor.getAvailable(), is(0));
    }
