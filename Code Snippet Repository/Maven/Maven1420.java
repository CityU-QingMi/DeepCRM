        private CacheItem getCacheEntry( Class<?> cls )
        {
            CacheItem cacheItem = CACHED_ENTRIES.get( cls );
            if ( cacheItem == null )
            {
                cacheItem = new CacheItem( cls );
                CACHED_ENTRIES.put( cls, cacheItem );
            }
            return cacheItem;
        }
