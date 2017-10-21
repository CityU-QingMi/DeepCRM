    @Test
    public void test_StartAsync_OnTimeout_CalledBy_PooledThread() throws Exception
    {
        String threadNamePrefix = "async_listener";
        threadPool = new QueuedThreadPool();
        threadPool.setName(threadNamePrefix);
        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(new ServletHolder(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                AsyncContext asyncContext = request.startAsync();
                asyncContext.setTimeout(1000);
                asyncContext.addListener(new AsyncListenerAdapter()
                {
                    @Override
                    public void onTimeout(AsyncEvent event) throws IOException
                    {
                        if (Thread.currentThread().getName().startsWith(threadNamePrefix))
                            response.setStatus(HttpStatus.OK_200);
                        else
                            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
                        asyncContext.complete();
                    }
                });
            }
        }), "/*");
        startServer(context);

        HttpTester.Response response = HttpTester.parseResponse(connector.getResponse("" +
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "\r\n"));
        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
