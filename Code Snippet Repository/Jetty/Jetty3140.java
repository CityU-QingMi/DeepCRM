    @Override
    public Session newSession(HttpServletRequest request, String id, long time, long maxInactiveMs)
    {
        if (LOG.isDebugEnabled()) LOG.debug("Creating new session id="+id);
        Session session = newSession(request, _sessionDataStore.newSessionData(id, time, time, time, maxInactiveMs));
        session.getSessionData().setLastNode(_context.getWorkerName());
        try
        {
            if (isSaveOnCreate() && _sessionDataStore != null)
                _sessionDataStore.store(id, session.getSessionData());
        }
        catch (Exception e)
        {
            LOG.warn("Save of new session {} failed", id, e);
        }
        return session;
    }
