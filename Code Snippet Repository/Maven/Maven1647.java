    protected void mergePluginContainer_Plugins( PluginContainer target, PluginContainer source,
                                                 boolean sourceDominant, Map<Object, Object> context )
    {
        List<Plugin> src = source.getPlugins();
        if ( !src.isEmpty() )
        {
            List<Plugin> tgt = target.getPlugins();
            Map<Object, Plugin> merged = new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( Plugin element : tgt )
            {
                Object key = getPluginKey( element );
                merged.put( key, element );
            }

            for ( Plugin element : src )
            {
                Object key = getPluginKey( element );
                if ( sourceDominant || !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setPlugins( new ArrayList<>( merged.values() ) );
        }
    }
