    protected void checkValidForRead () throws IllegalStateException
    {
        checkLocked();
        
        if (_state == State.INVALID)
            throw new IllegalStateException("Invalid for read: id="+_sessionData.getId()+" created="+_sessionData.getCreated()+" accessed="+_sessionData.getAccessed()+" lastaccessed="+_sessionData.getLastAccessed()+" maxInactiveMs="+_sessionData.getMaxInactiveMs()+" expiry="+_sessionData.getExpiry());
        
        if (_state == State.INVALIDATING)
            return;
        
        if (!isResident())
            throw new IllegalStateException("Invalid for read: id="+_sessionData.getId()+" not resident");
    }
