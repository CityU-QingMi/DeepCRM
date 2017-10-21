    @Deprecated
    public void setPhases( Map<String, String> phases )
    {
        Map<String, LifecyclePhase> lphases = new LinkedHashMap<>();
        for ( Map.Entry<String, String> e: phases.entrySet() )
        {
            lphases.put( e.getKey(), new LifecyclePhase( e.getValue() ) );
        }
        setLifecyclePhases( lphases );
    }
