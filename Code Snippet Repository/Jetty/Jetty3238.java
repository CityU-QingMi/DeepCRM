    public HttpSession newHttpSession(HttpServletRequest request)
    {
        long created=System.currentTimeMillis();
        String id =_sessionIdManager.newSessionId(request,created);      
        Session session = _sessionCache.newSession(request, id, created,  (_dftMaxIdleSecs>0?_dftMaxIdleSecs*1000L:-1));
        session.setExtendedId(_sessionIdManager.getExtendedId(id, request));
        session.getSessionData().setLastNode(_sessionIdManager.getWorkerName());
        
        try
        {
            _sessionCache.put(id, session);
            _sessionsCreatedStats.increment();  
            
            if (request.isSecure())
                session.setAttribute(Session.SESSION_CREATED_SECURE, Boolean.TRUE);
            
            if (_sessionListeners!=null)
            {
                HttpSessionEvent event=new HttpSessionEvent(session);
                for (HttpSessionListener listener : _sessionListeners)
                    listener.sessionCreated(event);
            }

            return session;
        }
        catch (Exception e)
        {
            LOG.warn(e);
            return null;
        }      
    }
