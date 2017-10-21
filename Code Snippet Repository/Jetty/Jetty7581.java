    public SessionHandler newSessionHandler()
    throws Exception
    {
        SessionHandler h = new SessionHandler();
        SessionCache c = _cacheFactory.getSessionCache(h);
        SessionDataStore s = _storeFactory.getSessionDataStore(h);
        c.setSessionDataStore(s);
        h.setSessionCache(c);
        return h;
    }
