    @Override
    public SessionData load(String id) throws Exception
    {
        SessionData d = null;


        try
        {
            //check to see if the session data is already in the cache
            d = _cache.load(id);
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }

        if (d != null)
            return d; //cache hit

        //cache miss - go get it from the store
        d = _store.load(id);

        return d;
    }
