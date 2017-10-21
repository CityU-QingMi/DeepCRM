    private ConcurrentMap<String, Object> ensureMap()
    {
        while (true)
        {
            ConcurrentMap<String, Object> map = map();
            if (map != null)
                return map;
            map = new ConcurrentHashMap<>();
            if (_map.compareAndSet(null, map))
                return map;
        }
    }
