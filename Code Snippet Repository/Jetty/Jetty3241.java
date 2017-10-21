    @Before
    public void setUp() throws Exception
    {
        server = new Server();
        connector = new ServerConnector(server,null,null,new ArrayByteBufferPool(64,2048,64*1024),1,1,new HttpConnectionFactory());
        connector.setIdleTimeout(100000);
        
        server.addConnector(connector);
        stacklessChannelLogging =new StacklessLogging(HttpChannel.class);
    }
