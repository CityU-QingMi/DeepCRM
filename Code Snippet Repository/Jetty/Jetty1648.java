    @Override
    public Runnable onSelected()
    {
/**/
/**/
/**/

        int readyOps = _key.readyOps();
        int oldInterestOps;
        int newInterestOps;
        try (Locker.Lock lock = _locker.lock())
        {
            _updatePending = true;
            // Remove the readyOps, that here can only be OP_READ or OP_WRITE (or both).
            oldInterestOps = _desiredInterestOps;
            newInterestOps = oldInterestOps & ~readyOps;
            _desiredInterestOps = newInterestOps;
        }

        boolean fillable = (readyOps & SelectionKey.OP_READ) != 0;
        boolean flushable = (readyOps & SelectionKey.OP_WRITE) != 0;

        if (LOG.isDebugEnabled())
            LOG.debug("onSelected {}->{} r={} w={} for {}", oldInterestOps, newInterestOps, fillable, flushable, this);

        // return task to complete the job
        Runnable task= fillable 
                ? (flushable 
                        ? _runCompleteWriteFillable 
                        : _runFillable)
                : (flushable 
                        ? _runCompleteWrite 
                        : null);

        if (LOG.isDebugEnabled())
            LOG.debug("task {}",task);
        return task;
    }
