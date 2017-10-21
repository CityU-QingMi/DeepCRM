    public Session getSession(String id)
    {
        try
        {
            Session session =  _sessionCache.get(id);
            if (session != null)
            {
                //If the session we got back has expired
                if (session.isExpiredAt(System.currentTimeMillis()))
                {
                    //Expire the session
                    try
                    {
                        session.invalidate();
                    }
                    catch (Exception e)
                    {
                        LOG.warn("Invalidating session {} found to be expired when requested", id, e);
                    }
                    
                    return null;
                }
                
                session.setExtendedId(_sessionIdManager.getExtendedId(id, null));
                //session.getSessionData().setLastNode(_sessionIdManager.getWorkerName());  //TODO write through the change of node?
            }
            return session;
        }
        catch (UnreadableSessionDataException e)
        {
            LOG.warn(e);
            try
            {
                //tell id mgr to remove session from all other contexts
                getSessionIdManager().invalidateAll(id);
            }
            catch (Exception x)
            {
                LOG.warn("Error cross-context invalidating unreadable session {}", id, x);
            }
            return null;
        }
        catch (Exception other)
        {
            LOG.warn(other);
            return null;
        }
    }
