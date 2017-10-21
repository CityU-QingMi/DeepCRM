    public void flushCache()
    {
        if (_cache!=null)
        {
            while (_cache.size()>0)
            {
                for (String path : _cache.keySet())
                {
                    CachedHttpContent content = _cache.remove(path);
                    if (content!=null)
                        content.invalidate();
                }
            }
        }
    }
