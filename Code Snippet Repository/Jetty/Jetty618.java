    @Test(expected = InterruptedException.class)
    public void testInterrupt() throws Exception
    {
        final long delay = 1000;
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                try
                {
                    baseRequest.setHandled(true);
                    if (request.getDispatcherType() != DispatcherType.ERROR)
                        TimeUnit.MILLISECONDS.sleep(2 * delay);
                }
                catch (InterruptedException x)
                {
                    throw new ServletException(x);
                }
            }
        });

        Request request = client.newRequest("localhost", connector.getLocalPort())
                .timeout(3 * delay, TimeUnit.MILLISECONDS)
                .scheme(scheme);

        final Thread thread = Thread.currentThread();
        new Thread(() ->
        {
            try
            {
                TimeUnit.MILLISECONDS.sleep(delay);
                thread.interrupt();
            }
            catch (InterruptedException x)
            {
                throw new RuntimeException(x);
            }
        }).start();

        request.send();
    }
