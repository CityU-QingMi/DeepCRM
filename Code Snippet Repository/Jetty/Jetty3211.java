    protected boolean access(long time)
    {
        try (Lock lock = _lock.lock())
        {
            if (!isValid())
                return false;
            _newSession=false;
            long lastAccessed = _sessionData.getAccessed();
            if (_sessionInactivityTimer != null)
                _sessionInactivityTimer.notIdle();
            _sessionData.setAccessed(time);
            _sessionData.setLastAccessed(lastAccessed);
           _sessionData.calcAndSetExpiry(time);
            if (isExpiredAt(time))
            {
                invalidate();
                return false;
            }
            _requests++;
            return true;
        }
    }
