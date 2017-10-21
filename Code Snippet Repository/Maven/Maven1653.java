    protected void mergeModel_Licenses( Model target, Model source, boolean sourceDominant,
                                        Map<Object, Object> context )
    {
        List<License> src = source.getLicenses();
        if ( !src.isEmpty() )
        {
            List<License> tgt = target.getLicenses();
            Map<Object, License> merged = new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( License element : tgt )
            {
                Object key = getLicenseKey( element );
                merged.put( key, element );
            }

            for ( License element : src )
            {
                Object key = getLicenseKey( element );
                if ( sourceDominant || !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setLicenses( new ArrayList<>( merged.values() ) );
        }
    }
