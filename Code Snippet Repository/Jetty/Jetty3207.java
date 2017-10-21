    public void renewId(HttpServletRequest request)
    {
        if (_handler == null)
            throw new IllegalStateException ("No session manager for session "+ _sessionData.getId());
        
        String id = null;
        String extendedId = null;
        try (Lock lock = _lock.lock())
        {
            checkValidForWrite(); //don't renew id on a session that is not valid
            id = _sessionData.getId(); //grab the values as they are now
            extendedId = getExtendedId();
        }
        
        String newId = _handler._sessionIdManager.renewSessionId(id, extendedId, request); 
        try (Lock lock = _lock.lock())
        {
            checkValidForWrite(); 
            _sessionData.setId(newId);
            setExtendedId(_handler._sessionIdManager.getExtendedId(newId, request));
        }
        setIdChanged(true);
    }
