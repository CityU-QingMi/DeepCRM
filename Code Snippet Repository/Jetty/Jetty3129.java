    @Override
    public String getObjectContextBasis()
    {
        if (_managed != null )
        {
            String basis = null;
            if (_managed instanceof ContextHandler)
            {
                ContextHandler handler = (ContextHandler)_managed;
                String context = getContextName(handler);
                if (context==null)
                    context=handler.getDisplayName();
                if (context!=null)
                    return context;
            }
            else if (_managed instanceof AbstractHandler)
            {
                AbstractHandler handler = (AbstractHandler)_managed;
                Server server = handler.getServer();
                if (server != null)
                {
                    ContextHandler context = 
                        AbstractHandlerContainer.findContainerOf(server,
                                ContextHandler.class, handler);
                    
                    if (context != null)
                        basis = getContextName(context);
                }
            }
            if (basis != null)
                return basis;
        }
        return super.getObjectContextBasis();
    }
