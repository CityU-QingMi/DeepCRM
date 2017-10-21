    private ContextHandlerCollection findContextHandlerCollection(Handler[] handlers)
    {
        if (handlers == null)
            return null;

        for (Handler handler : handlers)
        {
            if (handler instanceof ContextHandlerCollection)
            {
                return (ContextHandlerCollection) handler;
            }

            if (handler instanceof HandlerCollection)
            {
                HandlerCollection hc = (HandlerCollection) handler;
                ContextHandlerCollection chc = findContextHandlerCollection(hc.getHandlers());
                if (chc != null)
                    return chc;
            }
        }
        return null;
    }
