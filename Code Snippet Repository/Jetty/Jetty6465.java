    @Test
    public void testXmlResourceInit() throws Exception
    {
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        URL urls[] = new URL[]{
                MavenTestingUtils.getTestResourceDir("httpclient/simple").toURI().toURL()
        };
        URLClassLoader classLoader = new URLClassLoader(urls, parent);
        
        try (ThreadClassLoaderScope scope = new ThreadClassLoaderScope(classLoader))
        {
            WebSocketClient client = new WebSocketClient();
            try
            {
                client.start();
                HttpClient httpClient = client.getHttpClient();
                assertThat("HttpClient exists", httpClient, notNullValue());
                assertThat("HttpClient is started", httpClient.isStarted(), is(true));
                assertThat("HttpClient.connectTimeout", httpClient.getConnectTimeout(), is(5555L));
                
                SslContextFactory sslContextFactory = httpClient.getSslContextFactory();
                List<String> actualExcludedProtocols = Arrays.asList(sslContextFactory.getExcludeProtocols());
                assertThat("HttpClient.sslContextFactory.excludedProtocols", actualExcludedProtocols, hasItem("TLS/0.1"));
                
                Executor executor = httpClient.getExecutor();
                assertThat("Executor exists", executor, notNullValue());
                assertThat("Executor instanceof", executor, instanceOf(QueuedThreadPool.class));
                QueuedThreadPool threadPool = (QueuedThreadPool) executor;
                assertThat("QueuedThreadPool.name", threadPool.getName(), startsWith("XmlBasedClient@"));
            }
            finally
            {
                client.stop();
            }
        }
    }
