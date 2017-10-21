    @Before
    public void startServer() throws Exception
    {
        String keystore = MavenTestingUtils.getTestResourceFile("keystore").getAbsolutePath();
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(keystore);
        sslContextFactory.setKeyStorePassword("storepwd");
        sslContextFactory.setKeyManagerPassword("keypwd");

        server=new Server();
        HttpConnectionFactory http = new HttpConnectionFactory();
        http.setInputBufferSize(512);
        http.getHttpConfiguration().setRequestHeaderSize(512);
        connector=new ServerConnector(server, sslContextFactory, http);
        connector.setPort(0);
        connector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration().setSendDateHeader(false);

        server.addConnector(connector);
    }
