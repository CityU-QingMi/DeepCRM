    @Test
    public void testUpstreamTransformationBufferedGzipped() throws Exception
    {
        startServer(new EchoHttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                response.setHeader(HttpHeader.CONTENT_ENCODING.asString(), "gzip");
                super.service(request, response);
            }
        });
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected ContentTransformer newClientRequestContentTransformer(HttpServletRequest clientRequest, Request proxyRequest)
            {
                return new GZIPContentTransformer(new BufferingContentTransformer());
            }
        });
        startClient();

        DeferredContentProvider content = new DeferredContentProvider();
        Request request = client.newRequest("localhost", serverConnector.getLocalPort());
        FutureResponseListener listener = new FutureResponseListener(request);
        request.header(HttpHeader.CONTENT_ENCODING, "gzip")
                .content(content)
                .send(listener);
        byte[] bytes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(StandardCharsets.UTF_8);
        content.offer(ByteBuffer.wrap(gzip(bytes)));
        sleep(1000);
        content.close();

        ContentResponse response = listener.get(5, TimeUnit.SECONDS);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertArrayEquals(bytes, response.getContent());
    }
