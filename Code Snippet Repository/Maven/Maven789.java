    public CacheRecord put( Key key, List<Artifact> pluginArtifacts )
    {
        Validate.notNull( pluginArtifacts, "pluginArtifacts cannot be null" );

        assertUniqueKey( key );

        CacheRecord record =
            new CacheRecord( Collections.unmodifiableList( new ArrayList<>( pluginArtifacts ) ) );

        cache.put( key, record );

        return record;
    }
