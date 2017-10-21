    @Test
    public void testServer401() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                response.setStatus(HttpStatus.UNAUTHORIZED_401);
                response.setHeader(HttpHeader.WWW_AUTHENTICATE.asString(), "Basic realm=\"test\"");
            }
        });
        final AtomicBoolean transformed = new AtomicBoolean();
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected ContentTransformer newServerResponseContentTransformer(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse)
            {
                return new AfterContentTransformer()
                {
                    @Override
                    public boolean transform(Source source, Sink sink) throws IOException
                    {
                        transformed.set(true);
                        return false;
                    }
                };
            }
        });
        startClient();

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.UNAUTHORIZED_401, response.getStatus());
        Assert.assertFalse(transformed.get());
    }
