    @Test
    public void testHostHeader() throws Exception
    {
        final String host = "fooBar";
        final int port = 1313;
        final String authority = host + ":" + port;
        start(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                Assert.assertEquals(host, request.getServerName());
                Assert.assertEquals(port, request.getServerPort());
                Assert.assertEquals(authority, request.getHeader("Host"));
            }
        });

        Session session = newClient(new Session.Listener.Adapter());
        HostPortHttpField hostHeader = new HostPortHttpField(authority);
        MetaData.Request metaData = new MetaData.Request("GET", HttpScheme.HTTP, hostHeader, servletPath, HttpVersion.HTTP_2, new HttpFields());
        HeadersFrame frame = new HeadersFrame(metaData, null, true);
        final CountDownLatch latch = new CountDownLatch(1);
        session.newStream(frame, new Promise.Adapter<>(), new Stream.Listener.Adapter()
        {
            @Override
            public void onHeaders(Stream stream, HeadersFrame frame)
            {
                MetaData.Response response = (MetaData.Response)frame.getMetaData();
                Assert.assertEquals(200, response.getStatus());
                if (frame.isEndStream())
                    latch.countDown();
            }
        });

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
