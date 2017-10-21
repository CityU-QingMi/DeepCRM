    private void configureHandlers()
    {
        RequestLogHandler requestLogHandler = new RequestLogHandler();
        if (requestLog != null)
            requestLogHandler.setRequestLog(requestLog);

        contexts = (ContextHandlerCollection) server
                .getChildHandlerByClass(ContextHandlerCollection.class);
        if (contexts == null)
        {
            contexts = new ContextHandlerCollection();
            HandlerCollection handlers = (HandlerCollection) server
                    .getChildHandlerByClass(HandlerCollection.class);
            if (handlers == null)
            {
                handlers = new HandlerCollection();
                server.setHandler(handlers);
                handlers.setHandlers(new Handler[] { contexts, new DefaultHandler(),
                        requestLogHandler });
            }
            else
            {
                handlers.addHandler(contexts);
            }
        }
        
        //if there are any extra contexts to deploy
        if (contextHandlers != null && contextHandlers.getContextHandlers() != null)
        {
            for (ContextHandler c:contextHandlers.getContextHandlers())
                contexts.addHandler(c);
        }
    }
