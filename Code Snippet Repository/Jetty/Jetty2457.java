    @Test
    public void testServerException() throws Exception
    {
        try (StacklessLogging stackless = new StacklessLogging(HttpChannel.class))
        {
            prepareProxy();
            prepareServer(new HttpServlet()
            {
                @Override
                protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
                {
                    throw new ServletException("Expected Test Exception");
                }
            });

            ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                    .timeout(5, TimeUnit.SECONDS)
                    .send();

            Assert.assertEquals(500, response.getStatus());
        }
    }
