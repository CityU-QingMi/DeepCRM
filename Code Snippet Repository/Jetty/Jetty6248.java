    public static WebSocketContainer getWebSocketContainer()
    {
        ContextHandler.Context context = ContextHandler.getCurrentContext();
        if (context == null)
            return null;
        
        ContextHandler handler = ContextHandler.getContextHandler(context);
        if (handler == null)
            return null;
        
        if (!(handler instanceof ServletContextHandler))
            return null;
        
        return (javax.websocket.WebSocketContainer) handler.getServletContext().getAttribute("javax.websocket.server.ServerContainer");
    }
