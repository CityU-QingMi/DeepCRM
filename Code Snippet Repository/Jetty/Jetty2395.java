    @Test
    public void testDownstreamTransformationKnownContentLengthDroppingLastChunk() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                byte[] chunk = new byte[1024];
                int contentLength = 2 * chunk.length;
                response.setContentLength(contentLength);
                ServletOutputStream output = response.getOutputStream();
                output.write(chunk);
                output.flush();
                sleep(1000);
                output.write(chunk);
            }
        });
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected ContentTransformer newServerResponseContentTransformer(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse)
            {
                return new ContentTransformer()
                {
                    @Override
                    public void transform(ByteBuffer input, boolean finished, List<ByteBuffer> output) throws IOException
                    {
                        if (!finished)
                            output.add(input);
                    }
                };
            }
        });
        startClient();

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
