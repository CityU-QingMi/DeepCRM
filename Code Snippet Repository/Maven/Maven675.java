    public List<Lifecycle> getLifeCycles()
    {
        // ensure canonical order of standard lifecycles
        Map<String, Lifecycle> lifecycles = new LinkedHashMap<>( this.lifecycles );

        LinkedHashSet<String> lifecycleNames = new LinkedHashSet<>( Arrays.asList( STANDARD_LIFECYCLES ) );
        lifecycleNames.addAll( lifecycles.keySet() );

        ArrayList<Lifecycle> result = new ArrayList<>();
        for ( String name : lifecycleNames )
        {
            Lifecycle lifecycle = lifecycles.get( name );
            Preconditions.checkNotNull( "A lifecycle must have an id.", lifecycle.getId() );
            result.add( lifecycle );
        }

        return result;
    }
