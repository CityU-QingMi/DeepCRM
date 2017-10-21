    @Before
    public void init() throws Exception
    {
        threadPool = Executors.newCachedThreadPool();

        client = new HttpClient(new SslContextFactory(true));
        client.setMaxConnectionsPerDestination(1);
        File keyStore = MavenTestingUtils.getTestResourceFile("keystore.jks");
        sslContextFactory = client.getSslContextFactory();
        sslContextFactory.setKeyStorePath(keyStore.getAbsolutePath());
        sslContextFactory.setKeyStorePassword("storepwd");
        client.start();

        SSLContext sslContext = sslContextFactory.getSslContext();
        acceptor = (SSLServerSocket)sslContext.getServerSocketFactory().createServerSocket(0);

        int serverPort = acceptor.getLocalPort();

        proxy = new SimpleProxy(threadPool, "localhost", serverPort);
        proxy.start();
        logger.info(":{} <==> :{}", proxy.getPort(), serverPort);
    }
