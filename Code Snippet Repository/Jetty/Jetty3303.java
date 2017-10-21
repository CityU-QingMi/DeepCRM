    @Before
    public void init() throws Exception
    {
        _server = new Server();
        HttpConnectionFactory http = new HttpConnectionFactory();
        http.setInputBufferSize(1024);
        http.getHttpConfiguration().setRequestHeaderSize(512);
        http.getHttpConfiguration().setResponseHeaderSize(512);
        http.getHttpConfiguration().setOutputBufferSize(2048);
        http.getHttpConfiguration().addCustomizer(_customizer=new ForwardedRequestCustomizer());
        _connector = new LocalConnector(_server,http);
        _server.addConnector(_connector);
        _handler = new RequestHandler();
        _server.setHandler(_handler);

        _handler._checker = new RequestTester()
        {
            @Override
            public boolean check(HttpServletRequest request,HttpServletResponse response)
            {
                _wasSecure.set(request.isSecure());
                _sslSession.set(String.valueOf(request.getAttribute("javax.servlet.request.ssl_session_id")));
                _sslCertificate.set(String.valueOf(request.getAttribute("javax.servlet.request.cipher_suite")));
                _results.add(request.getScheme());
                _results.add(request.getServerName());
                _results.add(Integer.toString(request.getServerPort()));
                _results.add(request.getRemoteAddr());
                _results.add(Integer.toString(request.getRemotePort()));
                return true;
            }
        };
        
        _server.start();
    }
