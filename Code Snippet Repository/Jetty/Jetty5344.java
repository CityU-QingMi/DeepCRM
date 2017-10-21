    @Override
    public void run()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} run", this);
        boolean produce = false;
        try (Lock locked = _locker.lock())
        {
            _pending = false;
            if (!_idle && !_producing)
            {
                produce = _producing = true;
            }
        }

        if (produce)
            produceConsume();
    }
