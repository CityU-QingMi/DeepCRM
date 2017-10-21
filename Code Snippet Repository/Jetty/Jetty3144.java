    @Override
    public boolean delete(String id) throws Exception
    {
        //delete from the store
        boolean deleted = _store.delete(id);
        //and from the cache
        _cache.delete(id);
        
        return deleted;
    }
