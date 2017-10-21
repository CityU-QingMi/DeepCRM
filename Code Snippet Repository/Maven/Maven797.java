    public CacheRecord put( Key key, ClassRealm pluginRealm, List<Artifact> pluginArtifacts )
    {
        Validate.notNull( pluginRealm, "pluginRealm cannot be null" );
        Validate.notNull( pluginArtifacts, "pluginArtifacts cannot be null" );

        if ( cache.containsKey( key ) )
        {
            throw new IllegalStateException( "Duplicate plugin realm for plugin " + key );
        }

        CacheRecord record = new CacheRecord( pluginRealm, pluginArtifacts );

        cache.put( key, record );

        return record;
    }
