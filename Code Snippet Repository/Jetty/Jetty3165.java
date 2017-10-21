    @Override
    public void expireAll(String id)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Expiring {}",id);
        
        for (SessionHandler manager:getSessionHandlers())
        {
            manager.invalidate(id);
        }
    }
