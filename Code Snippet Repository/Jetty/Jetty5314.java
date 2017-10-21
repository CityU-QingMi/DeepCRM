    @Override
    public void execute(Runnable job)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("queue {}",job);
        if (!isRunning() || !_jobs.offer(job))
        {
            LOG.warn("{} rejected {}", this, job);
            throw new RejectedExecutionException(job.toString());
        }
        else
        {
            // Make sure there is at least one thread executing the job.
            if (getThreads() == 0)
                startThreads(1);
        }
    }
