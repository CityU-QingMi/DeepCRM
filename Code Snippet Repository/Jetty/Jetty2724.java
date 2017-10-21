    @BeforeClass
    public static void startServer()
    {
        _server = new Server();
        _connector = new LocalConnector(_server);
        _server.setConnectors(new Connector[]{_connector});

        ContextHandler _context = new ContextHandler();
        _session = new SessionHandler();

        TestLoginService _loginService = new TestLoginService(TEST_REALM);

        _loginService.putUser("fred",new Password("password"), IdentityService.NO_ROLES);
        _loginService.putUser("harry",new Password("password"), new String[] {"HOMEOWNER"});
        _loginService.putUser("chris",new Password("password"), new String[] {"CONTRACTOR"});
        _loginService.putUser("steven", new Password("password"), new String[] {"SALESCLERK"});
        

        _context.setContextPath("/ctx");
        _server.setHandler(_context);
        _context.setHandler(_session);

        _server.addBean(_loginService);
    }
