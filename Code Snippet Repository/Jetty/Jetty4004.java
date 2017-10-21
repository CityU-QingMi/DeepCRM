    private void test_StartAsync_Throw_OnError(IOConsumer<AsyncEvent> consumer) throws Exception
    {
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/ctx");
        context.addServlet(new ServletHolder(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                AsyncContext asyncContext = request.startAsync();
                asyncContext.setTimeout(0);
                asyncContext.addListener(new AsyncListenerAdapter()
                {
                    @Override
                    public void onError(AsyncEvent event) throws IOException
                    {
                        consumer.accept(event);
                    }
                });
                throw new QuietServletException(new TestRuntimeException());
            }
        }), "/path/*");
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
