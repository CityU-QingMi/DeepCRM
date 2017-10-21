    @Override
    public void shutdown ()
    {
        // loop over all the sessions in memory (a few times if necessary to catch sessions that have been
        // added while we're running
        int loop=100;
        while (!_sessions.isEmpty() && loop-- > 0)
        {
            for (Session session: _sessions.values())
            {
                //if we have a backing store so give the session to it to write out if necessary
                if (_sessionDataStore != null)
                {
                    session.willPassivate();
                    try
                    {
                        _sessionDataStore.store(session.getId(), session.getSessionData());
                    }
                    catch (Exception e)
                    {
                        LOG.warn(e);
                    }
                    doDelete (session.getId()); //remove from memory
                }
                else
                {
                    //not preserving sessions on exit
                    try
                    {
                        session.invalidate();
                    }
                    catch (Exception e)
                    {
                        LOG.ignore(e);
                    }
                }
            }
        }
    }
