    protected void mergePluginExecution_Goals( PluginExecution target, PluginExecution source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        List<String> src = source.getGoals();
        if ( !src.isEmpty() )
        {
            List<String> tgt = target.getGoals();
            List<String> merged = new ArrayList<>( tgt.size() + src.size() );
            merged.addAll( tgt );
            merged.addAll( src );
            target.setGoals( merged );
        }
    }
