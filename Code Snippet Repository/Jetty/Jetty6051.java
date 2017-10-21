    public Object getContextHandler()
    {
        try
        {
            // Equiv of: ContextHandler.Context context = ContextHandler.getCurrentContext()
            Class<?> clazzContextHandler = Class.forName("org.eclipse.jetty.server.handler.ContextHandler");
            Method methodGetContext = clazzContextHandler.getMethod("getCurrentContext");
            Object objContext = methodGetContext.invoke(null);
            if (objContext == null)
                return null;
            
            // Equiv of: ContextHandler handler = ContextHandler.getContextHandler(context);
            Class<?> clazzServletContext = Class.forName("javax.servlet.ServletContext");
            Method methodGetContextHandler = clazzContextHandler.getMethod("getContextHandler", clazzServletContext);
            return methodGetContextHandler.invoke(null, objContext);
        }
        catch (Throwable ignore)
        {
            LOG.ignore(ignore);
            return null;
        }
    }
