    protected void mergeModel_Developers( Model target, Model source, boolean sourceDominant,
                                          Map<Object, Object> context )
    {
        List<Developer> src = source.getDevelopers();
        if ( !src.isEmpty() )
        {
            List<Developer> tgt = target.getDevelopers();
            Map<Object, Developer> merged = new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( Developer element : tgt )
            {
                Object key = getDeveloperKey( element );
                merged.put( key, element );
            }

            for ( Developer element : src )
            {
                Object key = getDeveloperKey( element );
                if ( sourceDominant || !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setDevelopers( new ArrayList<>( merged.values() ) );
        }
    }
