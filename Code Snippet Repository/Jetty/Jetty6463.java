    @Test
    public void testDefaultInit() throws Exception
    {
        WebSocketClient client = new WebSocketClient();
        try
        {
            client.start();
            HttpClient httpClient = client.getHttpClient();
            assertThat("HttpClient exists", httpClient, notNullValue());
            assertThat("HttpClient is started", httpClient.isStarted(), is(true));
            Executor executor = httpClient.getExecutor();
            assertThat("Executor exists", executor, notNullValue());
            assertThat("Executor instanceof", executor, instanceOf(QueuedThreadPool.class));
            QueuedThreadPool threadPool = (QueuedThreadPool) executor;
            assertThat("QueuedThreadPool.name", threadPool.getName(), startsWith("WebSocketClient@"));
        }
        finally
        {
            client.stop();
        }
    }
