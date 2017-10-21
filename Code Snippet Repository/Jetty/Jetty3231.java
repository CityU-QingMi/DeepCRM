    public void sessionInactivityTimerExpired (Session session)
    {
        if (session == null)
            return;


        //check if the session is:
        //1. valid
        //2. expired
        //3. idle
        boolean expired = false;
        try (Lock lock = session.lockIfNotHeld())
        {
            if (session.getRequests() > 0)
                return; //session can't expire or be idle if there is a request in it
            
            if (LOG.isDebugEnabled())
                LOG.debug("Inspecting session {}, valid={}", session.getId(), session.isValid());
            
            if (!session.isValid())
                return; //do nothing, session is no longer valid
            
            if (session.isExpiredAt(System.currentTimeMillis()) && session.getRequests() <=0)
                expired = true;
        }

        if (expired)
        {
            //instead of expiring the session directly here, accumulate a list of 
            //session ids that need to be expired. This is an efficiency measure: as
            //the expiration involves the SessionDataStore doing a delete, it is 
            //most efficient if it can be done as a bulk operation to eg reduce
            //roundtrips to the persistent store. Only do this if the HouseKeeper that
            //does the scavenging is configured to actually scavenge
            if (_sessionIdManager.getSessionHouseKeeper() != null && _sessionIdManager.getSessionHouseKeeper().getIntervalSec() > 0)
            {
                _candidateSessionIdsForExpiry.add(session.getId());
                if (LOG.isDebugEnabled())LOG.debug("Session {} is candidate for expiry", session.getId());
            }
        }
        else 
            _sessionCache.checkInactiveSession(session); //if inactivity eviction is enabled the session will be deleted from the cache
    }
