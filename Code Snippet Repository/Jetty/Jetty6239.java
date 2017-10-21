    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(".getEndpointInstance({})",endpointClass);
        }
        
        try
        {
            // Since this is started via a ServiceLoader, this class has no Scope or context
            // that can be used to obtain a ObjectFactory from.
            return endpointClass.newInstance();
        }
        catch (IllegalAccessException e)
        {
            throw new InstantiationException(String.format("%s: %s",e.getClass().getName(),e.getMessage()));
        }
    }
