    private void changeInterests(int operation)
    {
/**/
/**/
/**/
/**/

        int oldInterestOps;
        int newInterestOps;
        boolean pending;
        try (Locker.Lock lock = _locker.lock())
        {
            pending = _updatePending;
            oldInterestOps = _desiredInterestOps;
            newInterestOps = oldInterestOps | operation;
            if (newInterestOps != oldInterestOps)
                _desiredInterestOps = newInterestOps;
        }

        if (LOG.isDebugEnabled())
            LOG.debug("changeInterests p={} {}->{} for {}", pending, oldInterestOps, newInterestOps, this);

        if (!pending && _selector!=null)
            _selector.submit(_runUpdateKey);
    }
