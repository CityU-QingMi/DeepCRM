    @Override
    public boolean isSystemResource(String name, URL url)
    {
        if (_systemClasses == null)
            loadSystemClasses();

        boolean result = _systemClasses.match(name,url);
        if (LOG.isDebugEnabled())
            LOG.debug("isSystemResource=={} {} {}",result,name,url);
        return result;
    }
