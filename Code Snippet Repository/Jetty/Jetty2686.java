    protected HttpSession renewSession(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession httpSession = request.getSession(false);

        if (_renewSession && httpSession!=null)
        {
            synchronized (httpSession)
            {
                //if we should renew sessions, and there is an existing session that may have been seen by non-authenticated users
                //(indicated by SESSION_SECURED not being set on the session) then we should change id
                if (httpSession.getAttribute(Session.SESSION_CREATED_SECURE)!=Boolean.TRUE)
                {
                    if (httpSession instanceof Session)
                    {
                        Session s = (Session)httpSession;
                        String oldId = s.getId();
                        s.renewId(request);
                        s.setAttribute(Session.SESSION_CREATED_SECURE, Boolean.TRUE);
                        if (s.isIdChanged() && response != null && (response instanceof Response))
                            ((Response)response).addCookie(s.getSessionHandler().getSessionCookie(s, request.getContextPath(), request.isSecure()));
                        LOG.debug("renew {}->{}",oldId,s.getId());
                    }
                    else
                        LOG.warn("Unable to renew session "+httpSession);
                    
                    return httpSession;
                }
            }
        }
        return httpSession;
    }
