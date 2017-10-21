    private void testClientRequestContentKnownLengthGzipped(int length, final boolean expectChunked) throws Exception
    {
        byte[] bytes = new byte[length];
        new Random().nextBytes(bytes);

        startServer(new EchoHttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                String transferEncoding = request.getHeader(HttpHeader.TRANSFER_ENCODING.asString());
                if (expectChunked)
                    Assert.assertNotNull(transferEncoding);
                else
                    Assert.assertNull(transferEncoding);
                response.setHeader(HttpHeader.CONTENT_ENCODING.asString(), "gzip");
                super.service(request, response);
            }
        });
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected ContentTransformer newClientRequestContentTransformer(HttpServletRequest clientRequest, Request proxyRequest)
            {
                return new GZIPContentTransformer(ContentTransformer.IDENTITY);
            }
        });
        startClient();

        byte[] gzipBytes = gzip(bytes);
        ContentProvider gzipContent = new BytesContentProvider(gzipBytes);

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .header(HttpHeader.CONTENT_ENCODING, "gzip")
                .content(gzipContent)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertArrayEquals(bytes, response.getContent());
    }
