    private void startReservedThread()
    {
        try
        {
            while (true)
            {
                int pending = _pending.get();
                if (pending >= _capacity)
                    return;
                if (_pending.compareAndSet(pending, pending + 1))
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("{} startReservedThread p={}", this, pending + 1);

                    _executor.execute(new ReservedThread());
                    return;
                }
            }
        }
        catch(RejectedExecutionException e)
        {
            LOG.ignore(e);
        }
    }
