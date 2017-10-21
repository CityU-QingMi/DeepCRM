    @Deprecated
    public static Map<String, String> toLegacyMap( Map<String, LifecyclePhase> lifecyclePhases )
    {
        if ( lifecyclePhases == null )
        {
            return null;
        }
        
        if ( lifecyclePhases.isEmpty() )
        {
            return Collections.emptyMap();
        }
        
        Map<String, String> phases = new LinkedHashMap<>();
        for ( Map.Entry<String, LifecyclePhase> e: lifecyclePhases.entrySet() )
        {
            phases.put( e.getKey(), e.getValue().toString() );
        }
        return phases;
    }
