    @Override
    protected void mergePlugin_Executions( Plugin target, Plugin source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        List<PluginExecution> src = source.getExecutions();
        if ( !src.isEmpty() )
        {
            List<PluginExecution> tgt = target.getExecutions();
            Map<Object, PluginExecution> merged =
                new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( PluginExecution element : src )
            {
                if ( sourceDominant
                                || ( element.getInherited() != null ? element.isInherited() : source.isInherited() ) )
                {
                    Object key = getPluginExecutionKey( element );
                    merged.put( key, element );
                }
            }

            for ( PluginExecution element : tgt )
            {
                Object key = getPluginExecutionKey( element );
                PluginExecution existing = merged.get( key );
                if ( existing != null )
                {
                    mergePluginExecution( element, existing, sourceDominant, context );
                }
                merged.put( key, element );
            }

            target.setExecutions( new ArrayList<>( merged.values() ) );
        }
    }
