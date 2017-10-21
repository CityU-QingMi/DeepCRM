    public void checkInactiveSession (Session session)
    {
        if (session == null)
            return;

      if (LOG.isDebugEnabled()) LOG.debug("Checking for idle {}", session.getId());
        try (Lock s = session.lock())
        {
            if (getEvictionPolicy() > 0 && session.isIdleLongerThan(getEvictionPolicy()) && session.isValid() && session.isResident() && session.getRequests() <= 0)
            {       
                //Be careful with saveOnInactiveEviction - you may be able to re-animate a session that was
                //being managed on another node and has expired.
                try
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Evicting idle session {}", session.getId());

                    //save before evicting
                    if (isSaveOnInactiveEviction() && _sessionDataStore != null)
                    {
                        if (_sessionDataStore.isPassivating())
                            session.willPassivate();

                        _sessionDataStore.store(session.getId(), session.getSessionData());
                    }
               
                    doDelete(session.getId()); //detach from this cache
                    session.setResident(false);
                }
                catch (Exception e)
                {
                    LOG.warn("Passivation of idle session {} failed", session.getId(), e);
                    session.updateInactivityTimer();
                }
            }
        }
    }
