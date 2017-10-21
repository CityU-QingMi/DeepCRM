    @Override
    public void setMaxInactiveInterval(int secs)
    {
        try (Lock lock = _lock.lock())
        {
            _sessionData.setMaxInactiveMs((long)secs*1000L);  
            _sessionData.calcAndSetExpiry();
            _sessionData.setDirty(true);
            updateInactivityTimer();
            if (LOG.isDebugEnabled())
            {
                if (secs <= 0)
                    LOG.debug("Session {} is now immortal (maxInactiveInterval={})", _sessionData.getId(), secs);
                else
                    LOG.debug("Session {} maxInactiveInterval={}", _sessionData.getId(), secs);
            }
        }
    }
