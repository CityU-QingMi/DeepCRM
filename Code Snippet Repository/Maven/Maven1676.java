    protected void mergeModel_Profiles( Model target, Model source, boolean sourceDominant,
                                        Map<Object, Object> context )
    {
        List<Profile> src = source.getProfiles();
        if ( !src.isEmpty() )
        {
            List<Profile> tgt = target.getProfiles();
            Map<Object, Profile> merged = new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( Profile element : tgt )
            {
                Object key = getProfileKey( element );
                merged.put( key, element );
            }

            for ( Profile element : src )
            {
                Object key = getProfileKey( element );
                if ( sourceDominant || !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setProfiles( new ArrayList<>( merged.values() ) );
        }
    }
