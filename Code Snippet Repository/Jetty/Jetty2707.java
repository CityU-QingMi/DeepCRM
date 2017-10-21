    @Before
    public  void startServer()
    {
        _server = new Server();
        
        HttpConnectionFactory http = new HttpConnectionFactory();
        http.getHttpConfiguration().setSecurePort(9999);
        http.getHttpConfiguration().setSecureScheme("BWTP");
        _connector = new LocalConnector(_server,http);
        _connector.setIdleTimeout(300000);

        HttpConnectionFactory https = new HttpConnectionFactory();
        https.getHttpConfiguration().addCustomizer(new HttpConfiguration.Customizer()
        {
            @Override
            public void customize(Connector connector, HttpConfiguration channelConfig, Request request)
            {
                request.setScheme(HttpScheme.HTTPS.asString());
                request.setSecure(true);
            }
        });
        
        _connectorS = new LocalConnector(_server,https);
        _server.setConnectors(new Connector[]{_connector,_connectorS});

        ContextHandler _context = new ContextHandler();
        _session = new SessionHandler();

        _context.setContextPath("/ctx");
        _server.setHandler(_context);
        _context.setHandler(_session);

        _security = new ConstraintSecurityHandler();
        _session.setHandler(_security);

        _security.setHandler(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.sendError(404);
            }
        });

    }
