    @Override
    protected void mergeModelBase_PluginRepositories( ModelBase target, ModelBase source, boolean sourceDominant,
                                                      Map<Object, Object> context )
    {
        List<Repository> src = source.getPluginRepositories();
        if ( !src.isEmpty() )
        {
            List<Repository> tgt = target.getPluginRepositories();
            Map<Object, Repository> merged = new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            List<Repository> dominant, recessive;
            if ( sourceDominant )
            {
                dominant = src;
                recessive = tgt;
            }
            else
            {
                dominant = tgt;
                recessive = src;
            }

            for ( Repository element : dominant )
            {
                Object key = getRepositoryKey( element );
                merged.put( key, element );
            }

            for ( Repository element : recessive )
            {
                Object key = getRepositoryKey( element );
                if ( !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setPluginRepositories( new ArrayList<>( merged.values() ) );
        }
    }
