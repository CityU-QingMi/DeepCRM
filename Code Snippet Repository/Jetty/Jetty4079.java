    @Test
    public void testQueryReplacedByForwardWithQuery() throws Exception
    {
        // 1. request /one?a=1
        // 1. forward /two?a=2
        // 2. assert query => a=2
        // 1. assert query => a=1

        CountDownLatch latch = new CountDownLatch(1);
        final String query1 = "a=1%20one&b=2%20two";
        final String query2 = "a=3%20three";
        final String query3 = "a=3%20three&b=2%20two";
        servlet1 = new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                checkThat(req.getQueryString(),Matchers.equalTo(query1));

                req.getRequestDispatcher("/two?" + query2).forward(req, resp);

                checkThat(req.getQueryString(), Matchers.equalTo(query1));
                checkThat(req.getParameter("a"),Matchers.equalTo("1 one"));
                checkThat(req.getParameter("b"),Matchers.equalTo("2 two"));
                latch.countDown();
            }
        };
        servlet2 = new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                checkThat(req.getQueryString(),Matchers.equalTo(query3));
                checkThat(req.getParameter("a"),Matchers.equalTo("3 three"));
                checkThat(req.getParameter("b"),Matchers.equalTo("2 two"));
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
