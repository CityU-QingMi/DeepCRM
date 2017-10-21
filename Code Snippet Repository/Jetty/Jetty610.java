    @Test
    public void test_CookieIsSent() throws Exception
    {
        final String name = "foo";
        final String value = "bar";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Cookie[] cookies = request.getCookies();
                Assert.assertNotNull(cookies);
                Assert.assertEquals(1, cookies.length);
                Cookie cookie = cookies[0];
                Assert.assertEquals(name, cookie.getName());
                Assert.assertEquals(value, cookie.getValue());
            }
        });

        String host = "localhost";
        int port = connector.getLocalPort();
        String path = "/path";
        String uri = scheme + "://" + host + ":" + port;
        HttpCookie cookie = new HttpCookie(name, value);
        client.getCookieStore().add(URI.create(uri), cookie);

        Response response = client.GET(scheme + "://" + host + ":" + port + path);
        Assert.assertEquals(200, response.getStatus());
    }
