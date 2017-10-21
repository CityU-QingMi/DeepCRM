    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        sce.getServletContext().addListener(_servletRequestListener);        
        ContextHandler handler =  ContextHandler.getContextHandler(sce.getServletContext());
        handler.addEventListener(_contextScopeListener);
        String cname=findContextName(sce.getServletContext());
        log("^  ctx=%s %s",cname,sce.getServletContext());
        if (_dumpContext)
        {
            if (_out==null)
                handler.dumpStdErr();
            else
            {
                try
                {
                    handler.dump(_out);
                }
                catch(Exception e)
                {
                    LOG.warn(e);
                }
            }
        }
    }
