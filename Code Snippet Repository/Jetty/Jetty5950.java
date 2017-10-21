    @Override
    public boolean isSystemClass(Class<?> clazz)
    {
        if (_systemClasses == null)
            loadSystemClasses();

        boolean result = _systemClasses.match(clazz);
        if (LOG.isDebugEnabled())
            LOG.debug("isSystemClass=={} {}",result,clazz);
        return result;
    }
