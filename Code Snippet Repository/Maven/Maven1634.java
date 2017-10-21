    protected void mergeBuild_Extensions( Build target, Build source, boolean sourceDominant,
                                          Map<Object, Object> context )
    {
        List<Extension> src = source.getExtensions();
        if ( !src.isEmpty() )
        {
            List<Extension> tgt = target.getExtensions();
            Map<Object, Extension> merged = new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( Extension element : tgt )
            {
                Object key = getExtensionKey( element );
                merged.put( key, element );
            }

            for ( Extension element : src )
            {
                Object key = getExtensionKey( element );
                if ( sourceDominant || !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setExtensions( new ArrayList<>( merged.values() ) );
        }
    }
