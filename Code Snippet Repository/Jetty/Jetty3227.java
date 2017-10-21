    public Session removeSession(String id, boolean invalidate)
    {
        try
        {
            //Remove the Session object from the session store and any backing data store
            Session session = _sessionCache.delete(id);
            if (session != null)
            {
                if (invalidate)
                {
                    session.beginInvalidate();
                    
                    if (_sessionListeners!=null)
                    {
                        HttpSessionEvent event=new HttpSessionEvent(session);      
                        for (int i = _sessionListeners.size()-1; i>=0; i--)
                        {
                            _sessionListeners.get(i).sessionDestroyed(event);
                        }
                    }
                }
            }
            //TODO if session object is not known to this node, how to get rid of it if no other
            //node knows about it?

            return session;
        }
        catch (Exception e)
        {
            LOG.warn(e);
            return null;
        }
    }
