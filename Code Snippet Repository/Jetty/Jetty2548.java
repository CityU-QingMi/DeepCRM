    protected void start(final boolean isSecure) throws Exception
    {
        _connector = new LocalConnector(_server);
        _connector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration().addCustomizer(new HttpConfiguration.Customizer()
        {
            @Override
            public void customize(Connector connector, HttpConfiguration channelConfig, Request request)
            {
                request.setSecure(isSecure);
            }
        });
        _server.setConnectors(new Connector[]{_connector});
        

        _server.setHandler(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                _request=baseRequest;
                _response=_request.getResponse();
                try
                {
                    _latch.await();
                }
                catch (InterruptedException e)
                {
                    throw new ServletException(e);
                }
            }
        });

        _server.start();

        _latch=new CountDownLatch(1);
        _connector.executeRequest("GET / HTTP/1.0\nCookie: set=already\n\n");
        
        while (_response==null)
            Thread.sleep(1);
    }
