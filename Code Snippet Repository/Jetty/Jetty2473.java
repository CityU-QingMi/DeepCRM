    @Test
    public void testProxyLongPoll() throws Exception
    {
        final long timeout = 1000;
        startServer(new HttpServlet()
        {
            @Override
            protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
            {
                if (!request.isAsyncStarted())
                {
                    final AsyncContext asyncContext = request.startAsync();
                    asyncContext.setTimeout(timeout);
                    asyncContext.addListener(new AsyncListener()
                    {
                        @Override
                        public void onComplete(AsyncEvent event) throws IOException
                        {
                        }

                        @Override
                        public void onTimeout(AsyncEvent event) throws IOException
                        {
                            if (request.getHeader("Via") != null)
                                response.addHeader(PROXIED_HEADER, "true");
                            asyncContext.complete();
                        }

                        @Override
                        public void onError(AsyncEvent event) throws IOException
                        {
                        }

                        @Override
                        public void onStartAsync(AsyncEvent event) throws IOException
                        {
                        }
                    });
                }
            }
        });
        startProxy();
        startClient();

        Response response = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(2 * timeout, TimeUnit.MILLISECONDS)
                .send();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getHeaders().containsKey(PROXIED_HEADER));
    }
