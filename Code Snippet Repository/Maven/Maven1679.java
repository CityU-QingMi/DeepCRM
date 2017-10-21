    protected void mergeModelBase_Dependencies( ModelBase target, ModelBase source, boolean sourceDominant,
                                                Map<Object, Object> context )
    {
        List<Dependency> src = source.getDependencies();
        if ( !src.isEmpty() )
        {
            List<Dependency> tgt = target.getDependencies();
            Map<Object, Dependency> merged = new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( Dependency element : tgt )
            {
                Object key = getDependencyKey( element );
                merged.put( key, element );
            }

            for ( Dependency element : src )
            {
                Object key = getDependencyKey( element );
                if ( sourceDominant || !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setDependencies( new ArrayList<>( merged.values() ) );
        }
    }
