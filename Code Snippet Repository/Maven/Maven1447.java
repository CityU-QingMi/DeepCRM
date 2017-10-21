    @Override
    protected void mergePluginExecution_Goals( PluginExecution target, PluginExecution source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        List<String> src = source.getGoals();
        if ( !src.isEmpty() )
        {
            List<String> tgt = target.getGoals();
            Set<String> excludes = new LinkedHashSet<>( tgt );
            List<String> merged = new ArrayList<>( tgt.size() + src.size() );
            merged.addAll( tgt );
            for ( String s : src )
            {
                if ( !excludes.contains( s ) )
                {
                    merged.add( s );
                }
            }
            target.setGoals( merged );
        }
    }
