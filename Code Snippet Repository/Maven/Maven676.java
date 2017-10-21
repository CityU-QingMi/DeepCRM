    public String getLifecyclePhaseList()
    {
        Set<String> phases = new LinkedHashSet<>();

        for ( Lifecycle lifecycle : getLifeCycles() )
        {
            phases.addAll( lifecycle.getPhases() );
        }

        return StringUtils.join( phases.iterator(), ", " );
    }
