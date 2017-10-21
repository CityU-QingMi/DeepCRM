    public CacheRecord put( Key key, Set<Artifact> projectArtifacts )
    {
        Validate.notNull( projectArtifacts, "projectArtifacts cannot be null" );

        assertUniqueKey( key );

        CacheRecord record =
            new CacheRecord( Collections.unmodifiableSet( new HashSet<>( projectArtifacts ) ) );

        cache.put( key, record );

        return record;
    }
