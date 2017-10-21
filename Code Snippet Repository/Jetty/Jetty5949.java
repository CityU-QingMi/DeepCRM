    public static WebAppContext getCurrentWebAppContext()
    {
        ContextHandler.Context context=ContextHandler.getCurrentContext();
        if (context!=null)
        {
            ContextHandler handler = context.getContextHandler();
            if (handler instanceof WebAppContext)
                return (WebAppContext)handler;
        }
        return null;
    }
