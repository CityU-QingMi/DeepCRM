    @Test
    public void testTransformGzippedHead() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                response.setHeader(HttpHeader.CONTENT_ENCODING.asString(), "gzip");

                String sample = "<a href=\"http://webtide.com/\">Webtide</a>\n<a href=\"http://google.com\">Google</a>\n";
                byte[] bytes = sample.getBytes(StandardCharsets.UTF_8);

                ServletOutputStream out = response.getOutputStream();
                out.write(gzip(bytes));

                // create a byte buffer larger enough to create 2 (or more) transforms.
                byte[] randomFiller = new byte[64 * 1024];
/**/
/**/
/**/
/**/
                new Random().nextBytes(randomFiller);

                out.write(gzip(randomFiller));
            }
        });
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected ContentTransformer newServerResponseContentTransformer(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse)
            {
                return new GZIPContentTransformer(new HeadTransformer());
            }
        });
        startClient();

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .header(HttpHeader.CONTENT_ENCODING, "gzip")
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());

        String expectedStr = "<a href=\"http://webtide.com/\">Webtide</a>";
        byte[] expected = expectedStr.getBytes(StandardCharsets.UTF_8);
        Assert.assertArrayEquals(expected, response.getContent());
    }
