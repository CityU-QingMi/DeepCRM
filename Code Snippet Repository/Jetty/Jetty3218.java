    protected void checkValidForWrite() throws IllegalStateException
    {    
        checkLocked();

        if (_state == State.INVALID)
            throw new IllegalStateException("Not valid for write: id="+_sessionData.getId()+" created="+_sessionData.getCreated()+" accessed="+_sessionData.getAccessed()+" lastaccessed="+_sessionData.getLastAccessed()+" maxInactiveMs="+_sessionData.getMaxInactiveMs()+" expiry="+_sessionData.getExpiry());
        
        if (_state == State.INVALIDATING)
            return;  //in the process of being invalidated, listeners may try to remove attributes
        
        if (!isResident())
            throw new IllegalStateException("Not valid for write: id="+_sessionData.getId()+" not resident");
    }
