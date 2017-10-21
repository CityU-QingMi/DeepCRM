    @Test
    public void testCustomizeHttpVersion() throws Exception
    {
        Server server = new Server();
        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.addCustomizer((connector, config, request) -> request.setHttpVersion(HttpVersion.HTTP_1_1));
        ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
        server.addConnector(connector);
        server.setHandler(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.setStatus(500);
                Assert.assertEquals(HttpVersion.HTTP_1_1.asString(), request.getProtocol());
                response.setStatus(200);
                response.getWriter().println("OK");
            }
        });
        server.start();

        try
        {
            try (SocketChannel socket = SocketChannel.open(new InetSocketAddress("localhost", connector.getLocalPort())))
            {
                HttpTester.Request request = HttpTester.newRequest();
                request.setVersion(HttpVersion.HTTP_1_0);
                socket.write(request.generate());

                HttpTester.Response response = HttpTester.parseResponse(HttpTester.from(socket));
                Assert.assertNotNull(response);
                Assert.assertThat(response.getStatus(), Matchers.equalTo(HttpStatus.OK_200));
            }
        }
        finally
        {
            server.stop();
        }
    }
