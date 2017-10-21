    @Override
    protected void doStop() throws Exception
    {
        decoderFactories.clear();
        handlers.clear();

        for (HttpDestination destination : destinations.values())
            destination.close();
        destinations.clear();

        requestListeners.clear();
        authenticationStore.clearAuthentications();
        authenticationStore.clearAuthenticationResults();

        super.doStop();
    }
