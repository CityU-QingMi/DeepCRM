    @Override
    public boolean isServerResource(String name, URL url)
    {
        if (_serverClasses == null)
            loadServerClasses();

        boolean result =  _serverClasses.match(name,url);
        if (LOG.isDebugEnabled())
            LOG.debug("isServerResource=={} {} {}",result,name,url);
        return result;
    }
