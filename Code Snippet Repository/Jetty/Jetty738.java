    public void removeAppProvider(AppProvider provider)
    {
        if(_providers.remove(provider))
            removeBean(provider);
        
        try
        {
            provider.stop();
        }
        catch (Exception e)
        {
            LOG.warn("Unable to stop Provider",e);
        }
    }
