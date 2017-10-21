    @Override
    public String changeSessionId()
    {
        HttpSession session = getSession(false);
        if (session == null)
            throw new IllegalStateException("No session");

        if (session instanceof Session)
        {
            Session s =  ((Session)session);
            s.renewId(this);
            if (getRemoteUser() != null)
                s.setAttribute(Session.SESSION_CREATED_SECURE, Boolean.TRUE);
            if (s.isIdChanged() && _sessionHandler.isUsingCookies())
                _channel.getResponse().addCookie(_sessionHandler.getSessionCookie(s, getContextPath(), isSecure()));
        }

        return session.getId();
    }
