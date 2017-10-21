    public boolean schedule(Scheduler scheduler)
    {
        long timeout = request.getTimeout();
        Scheduler.Task task = scheduler.schedule(this, timeout, TimeUnit.MILLISECONDS);
        Scheduler.Task existing = this.task.getAndSet(task);
        if (existing != null)
        {
            existing.cancel();
            cancel();
            throw new IllegalStateException();
        }
        if (LOG.isDebugEnabled())
            LOG.debug("Scheduled timeout task {} in {} ms for {}", task, timeout, request);
        return true;
    }
