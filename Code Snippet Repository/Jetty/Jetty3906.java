    public void setSecurityHandler(SecurityHandler securityHandler)
    {
        if (isStarted())
            throw new IllegalStateException("STARTED");

        Handler next=null;
        if (_securityHandler!=null)
        {
            next=_securityHandler.getHandler();
            _securityHandler.setHandler(null);
            replaceHandler(_securityHandler,securityHandler);
        }
        
        _securityHandler = securityHandler;
        if (next!=null && _securityHandler.getHandler()==null)
            _securityHandler.setHandler(next);
        relinkHandlers();
    }
