    @Test
    public void test_PerRequestCookieIsSent() throws Exception
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

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .cookie(new HttpCookie(name, value))
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertEquals(200, response.getStatus());
    }
