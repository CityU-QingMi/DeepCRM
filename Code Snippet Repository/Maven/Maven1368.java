    protected ProfileSelector newProfileSelector()
    {
        DefaultProfileSelector profileSelector = new DefaultProfileSelector();

        for ( ProfileActivator activator : newProfileActivators() )
        {
            profileSelector.addProfileActivator( activator );
        }

        return profileSelector;
    }
