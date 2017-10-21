    @Before
    public void startServer()
    {
        _server = new Server();
        _connector = new LocalConnector(_server);
        _config=_connector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration();
        _server.setConnectors(new Connector[]{_connector});

        ContextHandler _context = new ContextHandler();
        SessionHandler _session = new SessionHandler();

        TestLoginService _loginService = new TestLoginService(TEST_REALM);
        
        _loginService.putUser("user0", new Password("password"), new String[]{});
        _loginService.putUser("user",new Password("password"), new String[] {"user"});
        _loginService.putUser("user2",new Password("password"), new String[] {"user"});
        _loginService.putUser("admin",new Password("password"), new String[] {"user","administrator"});
        _loginService.putUser("user3", new Password("password"), new String[] {"foo"});
        

        _context.setContextPath("/ctx");
        _server.setHandler(_context);
        _context.setHandler(_session);

        _server.addBean(_loginService);

        _security = new ConstraintSecurityHandler();
        _session.setHandler(_security);
        RequestHandler _handler = new RequestHandler();
        _security.setHandler(_handler);

        _security.setConstraintMappings(getConstraintMappings(), getKnownRoles());
    }
