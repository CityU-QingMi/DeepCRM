    @Test
    public void testManualInit() throws Exception
    {
        HttpClient http = new HttpClient();
        {
            QueuedThreadPool threadPool = new QueuedThreadPool();
            threadPool.setName("ManualWSClient@" + http.hashCode());
            http.setExecutor(threadPool);
            http.setConnectTimeout(7777);
        }
        
        WebSocketClient client = new WebSocketClient(http);
        client.addBean(http);
        try
        {
            client.start();
            HttpClient httpClient = client.getHttpClient();
            assertThat("HttpClient exists", httpClient, notNullValue());
            assertThat("HttpClient is started", httpClient.isStarted(), is(true));
            assertThat("HttpClient.connectTimeout", httpClient.getConnectTimeout(), is(7777L));
            Executor executor = httpClient.getExecutor();
            assertThat("Executor exists", executor, notNullValue());
            assertThat("Executor instanceof", executor, instanceOf(QueuedThreadPool.class));
            QueuedThreadPool threadPool = (QueuedThreadPool) executor;
            assertThat("QueuedThreadPool.name", threadPool.getName(), startsWith("ManualWSClient@"));
        }
        finally
        {
            client.stop();
        }
    }
