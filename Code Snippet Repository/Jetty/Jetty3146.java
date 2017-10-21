    @Override
    public boolean exists(String id) throws Exception
    {
        try
        {
            //check the cache first
            SessionData data = _cache.load(id);
            if (data != null)
                return true;
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }

        //then the delegate store
        return _store.exists(id);
    }
