    @Override
    public <T extends AsyncListener> T createListener(Class<T> clazz) throws ServletException
    {    
        ContextHandler contextHandler = state().getContextHandler();
        if (contextHandler != null)
            return contextHandler.getServletContext().createListener(clazz);
        try
        {
            return clazz.newInstance();
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }
