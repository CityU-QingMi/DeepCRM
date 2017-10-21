    public void invalidate (String id)
    {
        if (StringUtil.isBlank(id))
            return;

        try
        {
            //remove the session and call the destroy listeners
            Session session = removeSession(id, true);

            if (session != null)
            {
                _sessionTimeStats.set(round((System.currentTimeMillis() - session.getSessionData().getCreated())/1000.0));
                session.finishInvalidate();   
            }
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }
