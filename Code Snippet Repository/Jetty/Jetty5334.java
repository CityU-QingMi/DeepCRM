    private void deactivate()
    {
        Scheduler.Task t = task.getAndSet(null);
        if (t != null)
        {
            boolean cancelled = t.cancel();
            if (LOG.isDebugEnabled())
                LOG.debug("Cancelled ({}) sweep task {}", cancelled, t);
        }
    }
