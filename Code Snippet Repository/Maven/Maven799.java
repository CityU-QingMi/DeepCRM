    public CacheRecord get( Key key )
        throws LifecycleExecutionException
    {
        CacheRecord cacheRecord = cache.get( key );

        if ( cacheRecord != null && cacheRecord.exception != null )
        {
            throw cacheRecord.exception;
        }

        return cacheRecord;
    }
