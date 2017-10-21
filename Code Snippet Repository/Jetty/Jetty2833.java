    ContextHandler getContextHandler(AsyncContextEvent event)
    {
        if (event!=null)
        {
            Context context=((Context)event.getServletContext());
            if (context!=null)
                return context.getContextHandler();
        }
        return null;
    }
