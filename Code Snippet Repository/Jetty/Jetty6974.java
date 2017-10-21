    @Deprecated
    public static WebSocketUpgradeFilter configureContext(ServletContext context) throws ServletException
    {
        ContextHandler handler = ContextHandler.getContextHandler(context);
        
        if (handler == null)
        {
            throw new ServletException("Not running on Jetty, WebSocket support unavailable");
        }
        
        if (!(handler instanceof ServletContextHandler))
        {
            throw new ServletException("Not running in Jetty ServletContextHandler, WebSocket support via " + WebSocketUpgradeFilter.class.getName() + " unavailable");
        }
        
        return configureContext((ServletContextHandler) handler);
    }
