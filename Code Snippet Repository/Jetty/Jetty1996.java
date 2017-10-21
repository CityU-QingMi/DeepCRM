    public static void configureHandlers (Server server, RequestLog requestLog) throws Exception 
    {
        if (server == null)
            throw new IllegalArgumentException ("Server is null");

        DefaultHandler defaultHandler = new DefaultHandler();
        RequestLogHandler requestLogHandler = new RequestLogHandler();
        if (requestLog != null)
            requestLogHandler.setRequestLog(requestLog);

        ContextHandlerCollection contexts = findContextHandlerCollection(server);
        if (contexts == null)
        {   
            contexts = new ContextHandlerCollection();
            HandlerCollection handlers = (HandlerCollection)server.getChildHandlerByClass(HandlerCollection.class);
            if (handlers == null)
            {
                handlers = new HandlerCollection();               
                server.setHandler(handlers);                            
                handlers.setHandlers(new Handler[]{contexts, defaultHandler, requestLogHandler});
            }
            else
            {
                handlers.addHandler(contexts);
            }
        }  
    }
