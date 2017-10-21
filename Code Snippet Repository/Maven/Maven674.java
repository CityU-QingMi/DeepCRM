    public Map<String, Lifecycle> getPhaseToLifecycleMap()
    {
        // If people are going to make their own lifecycles then we need to tell people how to namespace them correctly
        // so that they don't interfere with internally defined lifecycles.

        HashMap<String, Lifecycle> phaseToLifecycleMap = new HashMap<>();

        for ( Lifecycle lifecycle : getLifeCycles() )
        {
            if ( logger.isDebugEnabled() )
            {
                logger.debug( "Lifecycle " + lifecycle );
            }

            for ( String phase : lifecycle.getPhases() )
            {
                // The first definition wins.
                if ( !phaseToLifecycleMap.containsKey( phase ) )
                {
                    phaseToLifecycleMap.put( phase, lifecycle );
                }
                else
                {
                    Lifecycle original = phaseToLifecycleMap.get( phase );
                    logger.warn( "Duplicated lifecycle phase " + phase + ". Defined in " + original.getId()
                        + " but also in " + lifecycle.getId() );
                }
            }
        }

        return phaseToLifecycleMap;
    }
