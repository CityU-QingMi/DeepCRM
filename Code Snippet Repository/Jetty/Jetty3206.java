    @Override
    public void setAttribute(String name, Object value)
    {
        Object old=null;
        try (Lock lock = _lock.lock())
        {
            //if session is not valid, don't accept the set
            checkValidForWrite();
            old=_sessionData.setAttribute(name,value);
        }
        if (value == null && old == null)
            return; //if same as remove attribute but attribute was already removed, no change
        callSessionAttributeListeners(name, value, old);
    }
