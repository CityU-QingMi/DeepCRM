    @Override
    public boolean checkPathsWithUncoveredHttpMethods()
    {
        Set<String> paths = getPathsWithUncoveredHttpMethods();
        if (paths != null && !paths.isEmpty())
        {
            for (String p:paths)
                LOG.warn("{} has uncovered http methods for path: {}",ContextHandler.getCurrentContext(), p);
            if (LOG.isDebugEnabled())
                LOG.debug(new Throwable());
            return true;
        }
        return false; 
    }
