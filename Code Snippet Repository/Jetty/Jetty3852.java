    @Override
    public Resource getResource(String pathInContext)
    {
        Resource r=null;
        if (_relativeResourceBase!=null)
            pathInContext=URIUtil.addPaths(_relativeResourceBase,pathInContext);

        try
        {
            if (_resourceBase!=null)
            {
                r = _resourceBase.addPath(pathInContext);
                if (!_contextHandler.checkAlias(pathInContext,r))
                    r=null;
            }
            else if (_servletContext instanceof ContextHandler.Context)
            {
                r = _contextHandler.getResource(pathInContext);
            }
            else
            {
                URL u = _servletContext.getResource(pathInContext);
                r = _contextHandler.newResource(u);
            }

            if (LOG.isDebugEnabled())
                LOG.debug("Resource "+pathInContext+"="+r);
        }
        catch (IOException e)
        {
            LOG.ignore(e);
        }

        if((r==null || !r.exists()) && pathInContext.endsWith("/jetty-dir.css"))
            r=_stylesheet;

        return r;
    }
