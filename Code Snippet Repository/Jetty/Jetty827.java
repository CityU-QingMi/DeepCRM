    public List<WebAppContext> getWebAppContexts()
    {
        List<WebAppContext> contexts = new ArrayList<>();
        HandlerCollection handlers = (HandlerCollection)_server.getHandler();
        Handler children[] = handlers.getChildHandlers();

        for (Handler handler : children)
        {
            if (handler instanceof WebAppContext)
            {
                WebAppContext context = (WebAppContext)handler;
                contexts.add(context);
            }
        }

        return contexts;
    }
