    @Override
    public void onStartup(Set<Class<?>> c, ServletContext context) throws ServletException
    {
        ContextHandler handler = ContextHandler.getContextHandler(context);

        if (handler == null)
        {
            throw new ServletException("Not running on Jetty, WebSocket+CDI support unavailable");
        }

        if (!(handler instanceof ServletContextHandler))
        {
            throw new ServletException("Not running in Jetty ServletContextHandler, WebSocket+CDI support unavailable");
        }

        ServletContextHandler jettyContext = (ServletContextHandler)handler;
        try (ThreadClassLoaderScope scope = new ThreadClassLoaderScope(context.getClassLoader()))
        {
            addListeners(jettyContext);
        }
    }
