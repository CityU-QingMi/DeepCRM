    public CacheRecord get( Key key )
        throws PluginResolutionException
    {
        CacheRecord cacheRecord = cache.get( key );

        if ( cacheRecord != null && cacheRecord.exception != null )
        {
            throw cacheRecord.exception;
        }

        return cacheRecord;
    }
