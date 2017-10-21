    @Test
    public void test_CookieIsStored() throws Exception
    {
        final String name = "foo";
        final String value = "bar";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                response.addCookie(new Cookie(name, value));
                baseRequest.setHandled(true);
            }
        });

        String host = "localhost";
        int port = connector.getLocalPort();
        String path = "/path";
        String uri = scheme + "://" + host + ":" + port + path;
        Response response = client.GET(uri);
        Assert.assertEquals(200, response.getStatus());

        List<HttpCookie> cookies = client.getCookieStore().get(URI.create(uri));
        Assert.assertNotNull(cookies);
        Assert.assertEquals(1, cookies.size());
        HttpCookie cookie = cookies.get(0);
        Assert.assertEquals(name, cookie.getName());
        Assert.assertEquals(value, cookie.getValue());
    }
