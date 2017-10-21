    private void shrinkCache()
    {
        // While we need to shrink
        while (_cache.size()>0 && (_cachedFiles.get()>_maxCachedFiles || _cachedSize.get()>_maxCacheSize))
        {
            // Scan the entire cache and generate an ordered list by last accessed time.
            SortedSet<CachedHttpContent> sorted= new TreeSet<CachedHttpContent>(
                    new Comparator<CachedHttpContent>()
                    {
                        public int compare(CachedHttpContent c1, CachedHttpContent c2)
                        {
                            if (c1._lastAccessed<c2._lastAccessed)
                                return -1;
                            
                            if (c1._lastAccessed>c2._lastAccessed)
                                return 1;

                            if (c1._contentLengthValue<c2._contentLengthValue)
                                return -1;
                            
                            return c1._key.compareTo(c2._key);
                        }
                    });
            for (CachedHttpContent content : _cache.values())
                sorted.add(content);
            
            // Invalidate least recently used first
            for (CachedHttpContent content : sorted)
            {
                if (_cachedFiles.get()<=_maxCachedFiles && _cachedSize.get()<=_maxCacheSize)
                    break;
                if (content==_cache.remove(content.getKey()))
                    content.invalidate();
            }
        }
    }
