    @Before
    public void before() throws Exception
    {
        System.setProperty("org.apache.geronimo.jaspic.configurationFile","src/test/resources/jaspi.xml");
        _server = new Server();
        _connector = new LocalConnector(_server);
        _server.addConnector(_connector);
        
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        _server.setHandler(contexts);
        
        TestLoginService loginService = new TestLoginService("TestRealm");
        loginService.putUser("user",new Password("password"),new String[]{"users"});
        loginService.putUser("admin",new Password("secret"),new String[]{"users","admins"});
        _server.addBean(loginService);
        
        ContextHandler context = new ContextHandler();
        contexts.addHandler(context);
        context.setContextPath("/ctx");
        
        JaspiAuthenticatorFactory jaspiAuthFactory = new JaspiAuthenticatorFactory();
        
        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        context.setHandler(security);
        security.setAuthenticatorFactory(jaspiAuthFactory);
        // security.setAuthenticator(new BasicAuthenticator());
       
        Constraint constraint = new Constraint("All","users");
        constraint.setAuthenticate(true);
        ConstraintMapping mapping = new ConstraintMapping();
        mapping.setPathSpec("/jaspi/*");
        mapping.setConstraint(constraint);
        security.addConstraintMapping(mapping);
        
        TestHandler handler = new TestHandler();
        security.setHandler(handler);
        
        ContextHandler other = new ContextHandler();
        contexts.addHandler(other);
        other.setContextPath("/other");
        ConstraintSecurityHandler securityOther = new ConstraintSecurityHandler();
        other.setHandler(securityOther);
        securityOther.setAuthenticatorFactory(jaspiAuthFactory);
        securityOther.addConstraintMapping(mapping);        
        securityOther.setHandler(new TestHandler());
        
        _server.start();
    }
