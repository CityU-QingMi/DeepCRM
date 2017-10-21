    protected void mergeDependency_Exclusions( Dependency target, Dependency source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        List<Exclusion> src = source.getExclusions();
        if ( !src.isEmpty() )
        {
            List<Exclusion> tgt = target.getExclusions();

            Map<Object, Exclusion> merged = new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( Exclusion element : tgt )
            {
                Object key = getExclusionKey( element );
                merged.put( key, element );
            }

            for ( Exclusion element : src )
            {
                Object key = getExclusionKey( element );
                if ( sourceDominant || !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setExclusions( new ArrayList<>( merged.values() ) );
        }
    }
