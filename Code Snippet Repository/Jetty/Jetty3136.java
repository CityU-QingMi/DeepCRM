    @Override
    public Session delete(String id) throws Exception
    {
        //get the session, if its not in memory, this will load it
        Session session = get(id); 

 
        //Always delete it from the backing data store
        if (_sessionDataStore != null)
        {

            boolean dsdel = _sessionDataStore.delete(id);
            if (LOG.isDebugEnabled()) LOG.debug("Session {} deleted in session data store {}",id, dsdel);                   
        }
        
        //delete it from the session object store
        if (session != null)
        {
            session.stopInactivityTimer();
            session.setResident(false);
        }
        
        return doDelete(id);
    }
