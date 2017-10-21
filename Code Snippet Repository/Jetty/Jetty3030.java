    public void setContextPath(String contextPath)
    {
        if (contextPath == null)
            throw new IllegalArgumentException("null contextPath");

        if (contextPath.endsWith("/*"))
        {
            LOG.warn(this+" contextPath ends with /*");
            contextPath=contextPath.substring(0,contextPath.length()-2);
        }
        else if (contextPath.length()>1 && contextPath.endsWith("/"))
        {
            LOG.warn(this+" contextPath ends with /");
            contextPath=contextPath.substring(0,contextPath.length()-1);
        }

        if (contextPath.length()==0)
        {
            LOG.warn("Empty contextPath");
            contextPath="/";
        }

        _contextPath = contextPath;
        _contextPathEncoded = URIUtil.encodePath(contextPath);

        if (getServer() != null && (getServer().isStarting() || getServer().isStarted()))
        {
            Handler[] contextCollections = getServer().getChildHandlersByClass(ContextHandlerCollection.class);
            for (int h = 0; contextCollections != null && h < contextCollections.length; h++)
                ((ContextHandlerCollection)contextCollections[h]).mapContexts();
        }
    }
