    private void activate()
    {
        if (isRunning())
        {
            Scheduler.Task t = scheduler.schedule(this, period, TimeUnit.MILLISECONDS);
            if (LOG.isDebugEnabled())
                LOG.debug("Scheduled in {} ms sweep task {}", period, t);
            task.set(t);
        }
        else
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Skipping sweep task scheduling");
        }
    }
