    public void cancel()
    {
        Scheduler.Task task = this.task.getAndSet(null);
        if (task != null)
        {
            boolean cancelled = task.cancel();
            if (LOG.isDebugEnabled())
                LOG.debug("Cancelled (successfully: {}) timeout task {}", cancelled, task);
        }
    }
