    public void setSessionHandler(SessionHandler sessionHandler)
    {
        if (isStarted())
            throw new IllegalStateException("STARTED");

        Handler next=null;
        if (_sessionHandler!=null)
        {
            next=_sessionHandler.getHandler();
            _sessionHandler.setHandler(null);
            replaceHandler(_sessionHandler,sessionHandler);
        }

        _sessionHandler = sessionHandler;
        if (next!=null && _sessionHandler.getHandler()==null)
            _sessionHandler.setHandler(next);
        relinkHandlers();
    }
