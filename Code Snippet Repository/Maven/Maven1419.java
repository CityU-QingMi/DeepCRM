        private void traverseObjectWithParents( Class<?> cls, Object target )
        {
            if ( cls == null )
            {
                return;
            }

            CacheItem cacheEntry = getCacheEntry( cls );
            if ( cacheEntry.isArray() )
            {
                evaluateArray( target, this );
            }
            else if ( cacheEntry.isQualifiedForInterpolation )
            {
                cacheEntry.interpolate( target, this );

                traverseObjectWithParents( cls.getSuperclass(), target );
            }
        }
