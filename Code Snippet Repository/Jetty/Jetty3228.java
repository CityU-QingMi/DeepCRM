    public void renewSessionId(String oldId, String oldExtendedId, String newId, String newExtendedId)
    {
        try
        {
            Session session = _sessionCache.renewSessionId (oldId, newId); //swap the id over
            if (session == null)
            {
                //session doesn't exist on this context
                return;
            }
            
            session.setExtendedId(newExtendedId); //remember the extended id

            //inform the listeners
            if (!_sessionIdListeners.isEmpty())
            {
                HttpSessionEvent event = new HttpSessionEvent(session);
                for (HttpSessionIdListener l:_sessionIdListeners)
                {
                    l.sessionIdChanged(event, oldId);
                }
            }
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }
