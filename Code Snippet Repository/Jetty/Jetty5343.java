    @Override
    public void dispatch()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} spawning", this);
        boolean dispatch = false;
        try (Lock locked = _locker.lock())
        {
            if (_idle)
                dispatch = true;
            else
                _execute = true;
        }
        if (dispatch)
            _executor.execute(_runProduce);
    }
