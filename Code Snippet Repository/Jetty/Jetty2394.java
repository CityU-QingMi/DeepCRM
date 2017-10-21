    private void testDownstreamTransformationThrows(HttpServlet serverServlet) throws Exception
    {
        startServer(serverServlet);
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected ContentTransformer newServerResponseContentTransformer(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse)
            {
                return new ContentTransformer()
                {
                    private int count;

                    @Override
                    public void transform(ByteBuffer input, boolean finished, List<ByteBuffer> output) throws IOException
                    {
                        if (++count < 2)
                            output.add(input);
                        else
                            throw new NullPointerException("explicitly_thrown_by_test");
                    }
                };
            }
        });
        startClient();

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(502, response.getStatus());
    }
