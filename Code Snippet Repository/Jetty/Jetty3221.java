    public void copy (SessionData data)
    {
        if (data == null)
            return; //don't copy if no data

        if (data.getId() == null || !(getId().equals(data.getId())))
            throw new IllegalStateException ("Can only copy data for same session id");

        if (data == this)
            return; //don't copy ourself
        
        setLastNode(data.getLastNode());
        setContextPath(data.getContextPath());
        setVhost(data.getVhost());
        setCookieSet(data.getCookieSet());
        setCreated(data.getCreated());
        setAccessed(data.getAccessed());
        setLastAccessed(data.getLastAccessed());
        setMaxInactiveMs(data.getMaxInactiveMs());
        setExpiry(data.getExpiry());
        setLastSaved(data.getLastSaved());
        clearAllAttributes();
        putAllAttributes(data.getAllAttributes());
    }
