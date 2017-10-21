    @Test
    public void testProxyRequestExpired() throws Exception
    {
        prepareProxy();
        final long timeout = 1000;
        proxyServlet.setTimeout(timeout);
        prepareServer(new HttpServlet()
        {
            @Override
            protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
            {
                if (request.getHeader("Via") != null)
                    response.addHeader(PROXIED_HEADER, "true");
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

        Response response = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(3 * timeout, TimeUnit.MILLISECONDS)
                .send();
        Assert.assertEquals(504, response.getStatus());
        Assert.assertFalse(response.getHeaders().containsKey(PROXIED_HEADER));
    }
