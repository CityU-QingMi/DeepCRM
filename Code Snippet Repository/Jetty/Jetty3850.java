    protected ContextHandler initContextHandler(ServletContext servletContext)
    {
        ContextHandler.Context scontext=ContextHandler.getCurrentContext();
        if (scontext==null)
        {
            if (servletContext instanceof ContextHandler.Context)
                return ((ContextHandler.Context)servletContext).getContextHandler();
            else
                throw new IllegalArgumentException("The servletContext " + servletContext + " " +
                    servletContext.getClass().getName() + " is not " + ContextHandler.Context.class.getName());
        }
        else
            return ContextHandler.getCurrentContext().getContextHandler();
    }
