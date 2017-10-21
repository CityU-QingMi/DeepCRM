    @Override
    protected void doStop() throws Exception
    {
        // Stop all of the AppProviders
        for (AppProvider provider : _providers)
        {
            try
            {
                provider.stop();
            }
            catch (Exception e)
            {
                LOG.warn("Unable to start AppProvider",e);
            }
        }
        super.doStop();
    }
