    @Test
    public void testQueryRetainedByForwardWithoutQuery() throws Exception
    {
        // 1. request /one?a=1%20one
        // 1. forward /two
        // 2. assert query => a=1 one
        // 1. assert query => a=1 one

        CountDownLatch latch = new CountDownLatch(1);
        final String query1 = "a=1%20one";
        servlet1 = new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                checkThat(req.getQueryString(),Matchers.equalTo(query1));

                req.getRequestDispatcher("/two").forward(req, resp);

                checkThat(req.getQueryString(),Matchers.equalTo(query1));
                checkThat(req.getParameter("a"),Matchers.equalTo("1 one"));
                latch.countDown();
            }
        };
        servlet2 = new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                checkThat(req.getQueryString(),Matchers.equalTo(query1));
                checkThat(req.getParameter("a"),Matchers.equalTo("1 one"));
            }
        };

        prepare();

        String request = "" +
                "GET /one?" + query1 + " HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        String response = connector.getResponse(request);
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(response, response.startsWith("HTTP/1.1 200"));
    }
