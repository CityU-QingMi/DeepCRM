    public Object get(Object key)
    {
        Object value = null;
        CacheEntry entry;
        synchronized(this)
        {
            entry = cache.get(key);
        }
        if (entry != null)
        {
            if (environment.getCurrentTimeInMillis() - entry.getTimeCached() < timeout)
            {
                value = entry.getValue();
            }
            else
            {
                cache.remove(entry);
            }
        }
        return value;
    }
