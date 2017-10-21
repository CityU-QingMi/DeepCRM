    @Test(expected = TimeoutException.class)
    public void testClientRequestExpires() throws Exception
    {
        prepareProxy();
        final long timeout = 1000;
        proxyServlet.setTimeout(3 * timeout);
        prepareServer(new HttpServlet()
        {
            @Override
            protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
            {
                try
                {
                    TimeUnit.MILLISECONDS.sleep(2 * timeout);
                }
                catch (InterruptedException x)
                {
                    throw new ServletException(x);
                }
            }
        });

        client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(timeout, TimeUnit.MILLISECONDS)
                .send();
        Assert.fail();
    }
