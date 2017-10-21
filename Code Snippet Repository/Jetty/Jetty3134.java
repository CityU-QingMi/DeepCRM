    private Session loadSession (String id)
    throws Exception
    {
        SessionData data = null;
        Session session = null;

        if (_sessionDataStore == null)
            return null; //can't load it

        try
        {
            data =_sessionDataStore.load(id);

            if (data == null) //session doesn't exist
                return null;
            
            data.setLastNode(_context.getWorkerName());//we are going to manage the node
            session = newSession(data);
            return session;
        }
        catch (UnreadableSessionDataException e)
        {
            //can't load the session, delete it
            if (isRemoveUnloadableSessions())
                _sessionDataStore.delete(id);
            throw e;
        }
    }
