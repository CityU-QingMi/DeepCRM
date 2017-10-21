    @BeforeClass
    public static void setUp()
        throws Exception
    {
        _server = new Server();
        _connector = new ServerConnector(_server);
        _server.setConnectors(new Connector[] { _connector });

        _handler = new IPAccessHandler();
        _handler.setHandler(new AbstractHandler()
            {
                @Override
                public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
                {
                    baseRequest.setHandled(true);
                    response.setStatus(HttpStatus.OK_200);
                }
            });
        _server.setHandler(_handler);
        _server.start();
    }
