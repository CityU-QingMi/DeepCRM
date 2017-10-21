    public SessionData (String id, String cpath, String vhost, long created, long accessed, long lastAccessed, long maxInactiveMs, Map<String,Object> attributes)
    {
        _id = id;
        setContextPath(cpath);
        setVhost(vhost);
        _created = created;
        _accessed = accessed;
        _lastAccessed = lastAccessed;
        _maxInactiveMs = maxInactiveMs;
        calcAndSetExpiry();
        _attributes = attributes;
    }
