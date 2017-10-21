    @Test
    public void testServerClosesConnectionAfterRedirectWithoutConnectionCloseHeader() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                if (target.endsWith("/redirect"))
                {
                    response.setStatus(HttpStatus.TEMPORARY_REDIRECT_307);
                    response.setContentLength(0);
                    response.setHeader(HttpHeader.LOCATION.asString(), scheme + "://localhost:" + connector.getLocalPort() + "/");
                    response.flushBuffer();
                    baseRequest.getHttpChannel().getEndPoint().shutdownOutput();
                }
                else
                {
                    response.setStatus(HttpStatus.OK_200);
                    response.setContentLength(0);
                    response.setHeader(HttpHeader.CONNECTION.asString(), HttpHeaderValue.CLOSE.asString());
                }
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/redirect")
                .send();
        Assert.assertEquals(200, response.getStatus());
    }
