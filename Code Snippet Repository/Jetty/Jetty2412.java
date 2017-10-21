    @Test
    public void testTransformUpstreamAndDownstreamKnownContentLengthGzipped() throws Exception
    {
        String data = "<a href=\"http://google.com\">Google</a>";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

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
                return new GZIPContentTransformer(new HrefTransformer.Client());
            }

            @Override
            protected ContentTransformer newServerResponseContentTransformer(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse)
            {
                return new GZIPContentTransformer(new HrefTransformer.Server());
            }
        });
        startClient();

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .header(HttpHeader.CONTENT_ENCODING, "gzip")
                .content(new BytesContentProvider(gzip(bytes)))
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertArrayEquals(bytes, response.getContent());
    }
