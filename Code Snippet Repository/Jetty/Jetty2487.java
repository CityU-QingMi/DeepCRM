    @Test
    public void testHeadersListedByConnectionHeaderAreRemoved() throws Exception
    {
        final Map<String, String> hopHeaders = new LinkedHashMap<>();
        hopHeaders.put(HttpHeader.TE.asString(), "gzip");
        hopHeaders.put(HttpHeader.CONNECTION.asString(), "Keep-Alive, Foo, Bar");
        hopHeaders.put("Foo", "abc");
        hopHeaders.put("Foo", "def");
        hopHeaders.put(HttpHeader.KEEP_ALIVE.asString(), "timeout=30");
        startServer(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                List<String> names = Collections.list(request.getHeaderNames());
                for (String name : names)
                {
                    if (hopHeaders.containsKey(name))
                        throw new IOException("Hop header must not be proxied: " + name);
                }
            }
        });
        startProxy();
        startClient();

        Request request = client.newRequest("localhost", serverConnector.getLocalPort());
        for (Map.Entry<String, String> entry : hopHeaders.entrySet())
            request.header(entry.getKey(), entry.getValue());
        ContentResponse response = request
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
