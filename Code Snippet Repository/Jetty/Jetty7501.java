    protected void configureServer() throws Exception
    {
        _protocol = "http";
        _server.addBean(_loginService);

        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        _server.setHandler(security);

        Constraint constraint = new Constraint();
        constraint.setName("auth");
        constraint.setAuthenticate( true );
        constraint.setRoles(new String[]{"user", "admin"});

        ConstraintMapping mapping = new ConstraintMapping();
        mapping.setPathSpec( "/*" );
        mapping.setConstraint( constraint );

        Set<String> knownRoles = new HashSet<>();
        knownRoles.add("user");
        knownRoles.add("admin");

        security.setConstraintMappings(Collections.singletonList(mapping), knownRoles);
        security.setAuthenticator(new BasicAuthenticator());
        security.setLoginService(_loginService);

        ServletContextHandler root = new ServletContextHandler();
        root.setContextPath("/");
        root.setResourceBase(_resourceBase);
        ServletHolder servletHolder = new ServletHolder( new DefaultServlet() );
        servletHolder.setInitParameter( "gzip", "true" );
        root.addServlet( servletHolder, "/*" );

        _handler = new TestHandler(_resourceBase);

        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{_handler, root});
        security.setHandler(handlers);
    }
