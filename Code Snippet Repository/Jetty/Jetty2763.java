    protected boolean isCacheable(Resource resource)
    {
        if (_maxCachedFiles<=0)
            return false;
        
        long len = resource.length();

        // Will it fit in the cache?
        return  (len>0 && (_useFileMappedBuffer || (len<_maxCachedFileSize && len<_maxCacheSize)));
    }
