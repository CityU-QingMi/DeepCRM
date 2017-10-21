    private Map<String, LifecyclePhase> getLifecyclePhases( String lifecycle )
    {
        initLifecycleMap();

        Lifecycle lifecycleMapping = lifecycleMap.get( lifecycle );

        if ( lifecycleMapping != null )
        {
            return lifecycleMapping.getLifecyclePhases();
        }
        else if ( "default".equals( lifecycle ) )
        {
            return phases;
        }
        else
        {
            return null;
        }
    }
