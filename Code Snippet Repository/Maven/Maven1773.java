    public Lifecycle getLifecycleMapping( String lifecycleId )
        throws IOException, XmlPullParserException
    {
        if ( lifecycleMappings == null )
        {
            LifecycleConfiguration lifecycleConfiguration;

            try ( Reader reader = ReaderFactory.newXmlReader( getDescriptorStream( LIFECYCLE_DESCRIPTOR ) ) )
            {
                lifecycleConfiguration = new LifecycleMappingsXpp3Reader().read( reader );
            }

            lifecycleMappings = new HashMap<>();

            for ( Lifecycle lifecycle : lifecycleConfiguration.getLifecycles() )
            {
                lifecycleMappings.put( lifecycle.getId(), lifecycle );
            }
        }

        return lifecycleMappings.get( lifecycleId );
    }
