    public CacheRecord put( Key key, PluginResolutionException exception )
    {
        Validate.notNull( exception, "exception cannot be null" );

        assertUniqueKey( key );

        CacheRecord record = new CacheRecord( exception );

        cache.put( key, record );

        return record;
    }
