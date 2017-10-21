    @Override
    protected void doStart() throws Exception
    {
        if (getContexts()==null)
            throw new IllegalStateException("No Contexts");
        
        if (_useStandardBindings)
        {
            LOG.debug("DeploymentManager using standard bindings");
            addLifeCycleBinding(new StandardDeployer());
            addLifeCycleBinding(new StandardStarter());
            addLifeCycleBinding(new StandardStopper());
            addLifeCycleBinding(new StandardUndeployer());
        }

        // Start all of the AppProviders
        for (AppProvider provider : _providers)
        {
            startAppProvider(provider);
        }
        super.doStart();
    }
