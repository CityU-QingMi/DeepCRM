    private void startAppProvider(AppProvider provider)
    {
        try
        {
            provider.setDeploymentManager(this);
            provider.start();
        }
        catch (Exception e)
        {
            LOG.warn("Unable to start AppProvider",e);
        }
    }
