    protected void mergeBuildBase_Resources( BuildBase target, BuildBase source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        List<Resource> src = source.getResources();
        if ( !src.isEmpty() )
        {
            List<Resource> tgt = target.getResources();
            Map<Object, Resource> merged = new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( Resource element : tgt )
            {
                Object key = getResourceKey( element );
                merged.put( key, element );
            }

            for ( Resource element : src )
            {
                Object key = getResourceKey( element );
                if ( sourceDominant || !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setResources( new ArrayList<>( merged.values() ) );
        }
    }
