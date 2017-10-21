    public boolean dispatch(Runnable job)
    {
        try
        {
            _executor.execute(job);
            return true;
        }
        catch(RejectedExecutionException e)
        {
            LOG.warn(e);
            return false;
        }
    }
