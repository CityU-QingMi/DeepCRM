    public CacheRecord put( Key key, ClassRealm projectRealm, DependencyFilter extensionArtifactFilter )
    {
        Validate.notNull( projectRealm, "projectRealm cannot be null" );

        if ( cache.containsKey( key ) )
        {
            throw new IllegalStateException( "Duplicate project realm for extensions " + key );
        }

        CacheRecord record = new CacheRecord( projectRealm, extensionArtifactFilter );

        cache.put( key, record );

        return record;
    }
