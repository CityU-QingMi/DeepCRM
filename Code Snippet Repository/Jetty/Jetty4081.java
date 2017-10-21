    @Test
    public void testContentCanBeReadViaInputStreamAfterForwardWithoutQuery() throws Exception
    {
        CountDownLatch latch = new CountDownLatch(1);
        final String query1 = "a=1%20one";
        final String form = "c=3%20three";
        servlet1 = new HttpServlet()
        {
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                checkThat(req.getQueryString(),Matchers.equalTo(query1));

                req.getRequestDispatcher("/two").forward(req, resp);

                checkThat(req.getQueryString(),Matchers.equalTo(query1));
                checkThat(req.getParameter("c"), Matchers.nullValue());
                latch.countDown();
            }
        };
        servlet2 = new HttpServlet()
        {
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                checkThat(req.getQueryString(),Matchers.equalTo(query1));
                ServletInputStream input = req.getInputStream();
                for (int i = 0; i < form.length(); ++i)
                    checkThat(form.charAt(i) & 0xFFFF, Matchers.equalTo(input.read()));
            }
        };

        prepare();

        String request = "" +
                "POST /one?" + query1 + " HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Type: application/x-www-form-urlencoded\r\n" +
                "Content-Length: " + form.length() + "\r\n" +
                "Connection: close\r\n" +
                "\r\n" +
                form;
        String response = connector.getResponse(request);
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(response, response.startsWith("HTTP/1.1 200"));
    }
