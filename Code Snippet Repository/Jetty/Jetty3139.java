    public Session renewSessionId (String oldId, String newId)
    throws Exception
    {
        if (StringUtil.isBlank(oldId))
            throw new IllegalArgumentException ("Old session id is null");
        if (StringUtil.isBlank(newId))
            throw new IllegalArgumentException ("New session id is null");

        Session session = get(oldId);
        if (session == null)
            return null;

        try (Lock lock = session.lock())
        {
            session.checkValidForWrite(); //can't change id on invalid session
            session.getSessionData().setId(newId);
            session.getSessionData().setLastSaved(0); //pretend that the session has never been saved before to get a full save
            session.getSessionData().setDirty(true);  //ensure we will try to write the session out
            doPutIfAbsent(newId, session); //put the new id into our map
            doDelete (oldId); //take old out of map
            if (_sessionDataStore != null)
            {
                _sessionDataStore.delete(oldId);  //delete the session data with the old id
                _sessionDataStore.store(newId, session.getSessionData()); //save the session data with the new id
            }
            if (LOG.isDebugEnabled())
                LOG.debug ("Session id {} swapped for new id {}", oldId, newId);
            return session;
        }
    }
