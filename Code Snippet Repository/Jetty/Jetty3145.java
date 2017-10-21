    @Override
    public void store(String id, SessionData data) throws Exception
    {
        long lastSaved = data.getLastSaved();
        
        //write to the SessionDataStore first
        _store.store(id, data);

        //if the store saved it, then update the cache too
        if (data.getLastSaved() != lastSaved)
            _cache.store(id,data); 
    }
