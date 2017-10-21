    @BeforeClass
    public static void setUpServer()
    {
        try
        {
            _server = new Server();
            _server.setConnectors(new Connector[] { new ServerConnector(_server) });

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SECURITY);
            context.setContextPath("/test");
            context.addServlet(PostServlet.class,"/");

            TestLoginService realm = new TestLoginService("test");
            realm.putUser("testuser",new Password("password"),new String[]{"test"});
            _server.addBean(realm);
            
            ConstraintSecurityHandler security=(ConstraintSecurityHandler)context.getSecurityHandler();
            security.setAuthenticator(new DigestAuthenticator());
            security.setLoginService(realm);
           
            Constraint constraint = new Constraint("SecureTest","test");
            constraint.setAuthenticate(true);
            ConstraintMapping mapping = new ConstraintMapping();
            mapping.setConstraint(constraint);
            mapping.setPathSpec("/*");
            
            security.setConstraintMappings(Collections.singletonList(mapping));
            
            HandlerCollection handlers = new HandlerCollection();
            handlers.setHandlers(new Handler[]
            { context, new DefaultHandler() });
            _server.setHandler(handlers);
            
            _server.start();
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
