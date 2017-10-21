    public void stopInactivityTimer ()
    {
        try (Lock lock = _lock.lock())
        {
            if (_sessionInactivityTimer != null)
            {
                _sessionInactivityTimer.setIdleTimeout(-1);
                _sessionInactivityTimer = null;
                if (LOG.isDebugEnabled()) LOG.debug("Session timer stopped");
            }
        }
    }
