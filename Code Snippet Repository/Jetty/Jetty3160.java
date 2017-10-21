    @Override
    public boolean isIdInUse(String id)
    {
        if (id == null)
            return false;
        
        boolean inUse = false;
        if (LOG.isDebugEnabled())
            LOG.debug("Checking {} is in use by at least one context",id);

        try
        {
            for (SessionHandler manager:getSessionHandlers())
            {
                if (manager.isIdInUse(id))
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Context {} reports id in use", manager);
                    inUse = true;
                    break;
                }
            }
            
            if (LOG.isDebugEnabled())
                LOG.debug("Checked {}, in use:", id, inUse);
            return inUse;
        }
        catch (Exception e)
        {
            LOG.warn("Problem checking if id {} is in use", id, e);
            return false;
        }
    }
