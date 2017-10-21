    public void setIdleTimeout(long idleTimeout)
    {
        long old = _idleTimeout;
        _idleTimeout = idleTimeout;

        // Do we have an old timeout
        if (old > 0)
        {
            // if the old was less than or equal to the new timeout, then nothing more to do
            if (old <= idleTimeout)
                return;

            // old timeout is too long, so cancel it.
            deactivate();
        }

        // If we have a new timeout, then check and reschedule
        if (isOpen())
            activate();
    }
