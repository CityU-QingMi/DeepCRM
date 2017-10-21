    @Test
    public void testDownstreamTransformationBufferedGzipped() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                response.setHeader(HttpHeader.CONTENT_ENCODING.asString(), "gzip");

                ServletInputStream input = request.getInputStream();
                ServletOutputStream output = response.getOutputStream();
                int read;
                while ((read = input.read()) >= 0)
                {
                    output.write(read);
                    output.flush();
                }
            }
        });
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected ContentTransformer newServerResponseContentTransformer(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse)
            {
                return new GZIPContentTransformer(new BufferingContentTransformer());
            }
        });
        startClient();

        byte[] bytes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(StandardCharsets.UTF_8);
        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .header(HttpHeader.CONTENT_ENCODING, "gzip")
                .content(new BytesContentProvider(gzip(bytes)))
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertArrayEquals(bytes, response.getContent());
    }
