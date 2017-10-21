    @Test
    public void testUpstreamTransformationThrowsBeforeCommittingProxyRequest() throws Exception
    {
        startServer(new EchoHttpServlet());
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected ContentTransformer newClientRequestContentTransformer(HttpServletRequest clientRequest, Request proxyRequest)
            {
                return new ContentTransformer()
                {
                    @Override
                    public void transform(ByteBuffer input, boolean finished, List<ByteBuffer> output) throws IOException
                    {
                        throw new NullPointerException("explicitly_thrown_by_test");
                    }
                };
            }
        });
        startClient();

        byte[] bytes = new byte[1024];
        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .content(new BytesContentProvider(bytes))
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(500, response.getStatus());
    }
