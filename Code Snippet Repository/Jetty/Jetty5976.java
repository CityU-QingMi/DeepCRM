    @Override
    public boolean isServerClass(Class<?> clazz)
    {
        if (_serverClasses == null)
            loadServerClasses();

        boolean result = _serverClasses.match(clazz);
        if (LOG.isDebugEnabled())
            LOG.debug("isServerClass=={} {}",result,clazz);
        return result;
    }
