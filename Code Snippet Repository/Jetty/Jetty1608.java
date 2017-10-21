    @Override
    public void doStore(String id, SessionData data, long lastSaveTime) throws Exception
    {
        try
        {
        //Put an idle timeout on the cache entry if the session is not immortal - 
        //if no requests arrive at any node before this timeout occurs, or no node 
        //scavenges the session before this timeout occurs, the session will be removed.
        //NOTE: that no session listeners can be called for this.
        if (data.getMaxInactiveMs() > 0 && getInfinispanIdleTimeoutSec() > 0)
            _cache.put(getCacheKey(id), data, -1, TimeUnit.MILLISECONDS, getInfinispanIdleTimeoutSec(), TimeUnit.SECONDS);
        else
            _cache.put(getCacheKey(id), data);

        if (LOG.isDebugEnabled())
            LOG.debug("Session {} saved to infinispan, expires {} ", id, data.getExpiry());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
