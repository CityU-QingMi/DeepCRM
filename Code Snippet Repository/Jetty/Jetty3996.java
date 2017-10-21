    private void test_StartAsync_OnTimeout(long timeout, IOConsumer<AsyncEvent> consumer) throws Exception
    {
        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(new ServletHolder(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                AsyncContext asyncContext = request.startAsync();
                asyncContext.setTimeout(timeout);
                asyncContext.addListener(new AsyncListenerAdapter()
                {
                    @Override
                    public void onTimeout(AsyncEvent event) throws IOException
                    {
                        consumer.accept(event);
                    }
                });
            }
        }), "/*");
        context.addServlet(new ServletHolder(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                response.setStatus(HttpStatus.OK_200);
            }
        }), "/dispatch/*");
        context.addServlet(new ServletHolder(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                response.getOutputStream().print("CUSTOM");
            }
        }), "/error/*");

        startServer(context);
    }
