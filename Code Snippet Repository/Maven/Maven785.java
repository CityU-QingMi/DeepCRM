    public CacheRecord put( Key key, ClassRealm extensionRealm, ExtensionDescriptor extensionDescriptor,
                            List<Artifact> artifacts )
    {
        Validate.notNull( extensionRealm, "extensionRealm cannot be null" );

        if ( cache.containsKey( key ) )
        {
            throw new IllegalStateException( "Duplicate extension realm for extension " + key );
        }

        CacheRecord record = new CacheRecord( extensionRealm, extensionDescriptor, artifacts );

        cache.put( key, record );

        return record;
    }
