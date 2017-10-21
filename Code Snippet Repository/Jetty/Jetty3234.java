    public void complete(HttpSession session)
    {
        if (session == null)
            return;
        
        Session s = ((SessionIf)session).getSession();
    
        try
        {
            s.complete();
            _sessionCache.put(s.getId(), s);
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }
