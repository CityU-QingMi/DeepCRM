    public ContextHandler addContext(String contextPath,String resourceBase)
    {
        try
        {
            ContextHandler context = _contextClass.newInstance();
            context.setContextPath(contextPath);
            context.setResourceBase(resourceBase);
            addHandler(context);
            return context;
        }
        catch (Exception e)
        {
            LOG.debug(e);
            throw new Error(e);
        }
    }
