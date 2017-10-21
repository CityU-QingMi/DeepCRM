    @Override
    public HttpSession getSession(boolean create)
    {
        if (_session != null)
        {
            if (_sessionHandler != null && !_sessionHandler.isValid(_session))
                _session = null;
            else
                return _session;
        }

        if (!create)
            return null;

        if (getResponse().isCommitted())
            throw new IllegalStateException("Response is committed");

        if (_sessionHandler == null)
            throw new IllegalStateException("No SessionManager");

        _session = _sessionHandler.newHttpSession(this);
        HttpCookie cookie = _sessionHandler.getSessionCookie(_session,getContextPath(),isSecure());
        if (cookie != null)
            _channel.getResponse().addCookie(cookie);

        return _session;
    }
