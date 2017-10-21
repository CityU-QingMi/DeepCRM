    @Test
    public void testRequestHasHTTP2Version() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                HttpVersion version = HttpVersion.fromString(request.getProtocol());
                response.setStatus(version == HttpVersion.HTTP_2 ? HttpStatus.OK_200 : HttpStatus.INTERNAL_SERVER_ERROR_500);
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .onRequestBegin(request ->
                {
                    if (request.getVersion() != HttpVersion.HTTP_2)
                        request.abort(new Exception("Not a HTTP/2 request"));
                })
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
