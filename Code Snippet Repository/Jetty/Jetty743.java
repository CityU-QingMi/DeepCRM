    public void setAppProviders(Collection<AppProvider> providers)
    {
        if (isRunning())
            throw new IllegalStateException();
        
        _providers.clear();
        removeBeans();
        for (AppProvider provider:providers)
            if (_providers.add(provider))
                addBean(provider);
    }
