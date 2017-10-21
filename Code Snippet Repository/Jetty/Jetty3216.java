    public void updateInactivityTimer ()
    {
        try (Lock lock = _lock.lock())
        {
            if (LOG.isDebugEnabled())LOG.debug("updateInactivityTimer");

            long maxInactive =  _sessionData.getMaxInactiveMs();        
            int evictionPolicy = getSessionHandler().getSessionCache().getEvictionPolicy();

            if (maxInactive <= 0)
            {
                //sessions are immortal, they never expire
                if (evictionPolicy < SessionCache.EVICT_ON_INACTIVITY)
                {
                    //we do not want to evict inactive sessions
                    setInactivityTimer(-1L);
                    if (LOG.isDebugEnabled()) LOG.debug("Session is immortal && no inactivity eviction: timer cancelled");
                }
                else
                {
                    //sessions are immortal but we want to evict after inactivity
                    setInactivityTimer(TimeUnit.SECONDS.toMillis(evictionPolicy));
                    if (LOG.isDebugEnabled()) LOG.debug("Session is immortal; evict after {} sec inactivity", evictionPolicy);
                }                    
            }
            else
            {
                //sessions are not immortal
                if (evictionPolicy < SessionCache.EVICT_ON_INACTIVITY)
                {
                    //don't want to evict inactive sessions, set the timer for the session's maxInactive setting
                    setInactivityTimer(_sessionData.getMaxInactiveMs());
                    if (LOG.isDebugEnabled()) LOG.debug("No inactive session eviction");
                }
                else
                {
                    //set the time to the lesser of the session's maxInactive and eviction timeout
                    setInactivityTimer(Math.min(maxInactive, TimeUnit.SECONDS.toMillis(evictionPolicy)));
                    if (LOG.isDebugEnabled()) LOG.debug("Inactivity timer set to lesser of maxInactive={} and inactivityEvict={}", maxInactive, evictionPolicy);
                }
            }
        }
    }
