    protected void stopScheduler()
    {
        try
        {
            _scheduler.stop();
        }
        catch (Exception x)
        {
            LOG.ignore(x);
        }
    }
