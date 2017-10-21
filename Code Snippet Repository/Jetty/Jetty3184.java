    public void scavenge ()
    {
        //don't attempt to scavenge if we are shutting down
        if (isStopping() || isStopped())
            return;

        if (LOG.isDebugEnabled())
            LOG.debug("{} scavenging sessions", this);
        
        //find the session managers
        for (SessionHandler manager:_sessionIdManager.getSessionHandlers())
        {
            if (manager != null)
            {
                try
                {
                    manager.scavenge();
                }
                catch (Exception e)
                {
                    LOG.warn(e);
                }
            }
        }
    }
