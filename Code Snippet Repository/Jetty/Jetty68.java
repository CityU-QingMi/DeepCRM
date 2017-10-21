    public ServletContainerInitializerOrdering getInitializerOrdering (WebAppContext context)
    {
        if (context == null)
            return null;
        
        String tmp = (String)context.getAttribute(SERVLET_CONTAINER_INITIALIZER_ORDER);
        if (tmp == null || "".equals(tmp.trim()))
            return null;
        
        return new ServletContainerInitializerOrdering(tmp);
    }
