    @Override
    public String getWelcomeFile(String pathInContext)
    {
        if (_welcomes==null)
            return null;

        String welcome_servlet=null;
        for (int i=0;i<_welcomes.length;i++)
        {
            String welcome_in_context=URIUtil.addPaths(pathInContext,_welcomes[i]);
            Resource welcome=getResource(welcome_in_context);
            if (welcome!=null && welcome.exists())
                return welcome_in_context;

            if ((_welcomeServlets || _welcomeExactServlets) && welcome_servlet==null)
            {
                MappedResource<ServletHolder> entry=_servletHandler.getMappedServlet(welcome_in_context);
                if (entry!=null && entry.getResource()!=_defaultHolder &&
                        (_welcomeServlets || (_welcomeExactServlets && entry.getPathSpec().getDeclaration().equals(welcome_in_context))))
                    welcome_servlet=welcome_in_context;

            }
        }
        return welcome_servlet;
    }
