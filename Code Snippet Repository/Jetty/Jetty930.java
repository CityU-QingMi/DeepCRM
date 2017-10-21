    @Before
    public void initialize()
        throws Exception
    {

        server = new Server();
        serverConnector = new ServerConnector( server, new HttpConnectionFactory() );
        server.addConnector( serverConnector );

        ServletContextHandler context = new ServletContextHandler( ServletContextHandler.SESSIONS );
        context.setContextPath( contextPath );
        context.setResourceBase( System.getProperty( "java.io.tmpdir" ) );
        server.setHandler( context );

        SessionContext sessionContext = new SessionContext( "foo", null );

        hazelcastSessionDataStoreFactory = new HazelcastSessionDataStoreFactory();
        hazelcastSessionDataStore = (HazelcastSessionDataStore) hazelcastSessionDataStoreFactory.getSessionDataStore(
            context.getSessionHandler() );
        hazelcastSessionDataStore.initialize( sessionContext );

        DefaultSessionCache defaultSessionCache = new DefaultSessionCache( context.getSessionHandler() );
        defaultSessionCache.setSessionDataStore( hazelcastSessionDataStore );
        context.getSessionHandler().setSessionCache( defaultSessionCache );
        // Add a test servlet
        context.addServlet( new ServletHolder( new TestServlet() ), contextPath );

        server.start();
    }
