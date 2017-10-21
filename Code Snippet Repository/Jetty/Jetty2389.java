    @Test
    public void testDiscardUpstreamAndDownstreamKnownContentLengthGzipped() throws Exception
    {
        final byte[] bytes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(StandardCharsets.UTF_8);
        startServer(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                // decode input stream thru gzip
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                IO.copy(new GZIPInputStream(request.getInputStream()), bos);
                // ensure decompressed is 0 length
                Assert.assertEquals(0, bos.toByteArray().length);
                response.setHeader(HttpHeader.CONTENT_ENCODING.asString(), "gzip");
                response.getOutputStream().write(gzip(bytes));
            }
        });
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected ContentTransformer newClientRequestContentTransformer(HttpServletRequest clientRequest, Request proxyRequest)
            {
                return new GZIPContentTransformer(new DiscardContentTransformer());
            }

            @Override
            protected ContentTransformer newServerResponseContentTransformer(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse)
            {
                return new GZIPContentTransformer(new DiscardContentTransformer());
            }
        });
        startClient();

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .header(HttpHeader.CONTENT_ENCODING, "gzip")
                .content(new BytesContentProvider(gzip(bytes)))
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(0, response.getContent().length);
    }
