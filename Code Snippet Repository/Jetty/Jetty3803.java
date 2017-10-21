    @Test
    public void testShutdownRequestNotFromLocalhost() throws Exception
    {
        start(new HandlerWrapper()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setRemoteAddr(new InetSocketAddress("192.168.0.1", 12345));
                super.handle(target, baseRequest, request, response);
            }
        });

        HttpTester.Response response = shutdown(shutdownToken);
        Assert.assertEquals(HttpStatus.UNAUTHORIZED_401, response.getStatus());

        Thread.sleep(1000);
        Assert.assertEquals(AbstractLifeCycle.STARTED, server.getState());
    }
