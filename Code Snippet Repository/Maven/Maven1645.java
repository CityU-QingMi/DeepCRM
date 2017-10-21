    protected void mergeBuildBase_TestResources( BuildBase target, BuildBase source, boolean sourceDominant,
                                                 Map<Object, Object> context )
    {
        List<Resource> src = source.getTestResources();
        if ( !src.isEmpty() )
        {
            List<Resource> tgt = target.getTestResources();
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

            target.setTestResources( new ArrayList<>( merged.values() ) );
        }
    }
