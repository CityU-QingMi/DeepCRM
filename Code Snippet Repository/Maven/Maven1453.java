    @Override
    protected void mergeModelBase_Modules( ModelBase target, ModelBase source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        List<String> src = source.getModules();
        if ( !src.isEmpty() && sourceDominant )
        {
            List<Integer> indices = new ArrayList<>();
            List<String> tgt = target.getModules();
            Set<String> excludes = new LinkedHashSet<>( tgt );
            List<String> merged = new ArrayList<>( tgt.size() + src.size() );
            merged.addAll( tgt );
            for ( int i = 0, n = tgt.size(); i < n; i++ )
            {
                indices.add( i );
            }
            for ( int i = 0, n = src.size(); i < n; i++ )
            {
                String s = src.get( i );
                if ( !excludes.contains( s ) )
                {
                    merged.add( s );
                    indices.add( ~i );
                }
            }
            target.setModules( merged );
            target.setLocation( "modules", InputLocation.merge( target.getLocation( "modules" ),
                                                                source.getLocation( "modules" ), indices ) );
        }
    }
