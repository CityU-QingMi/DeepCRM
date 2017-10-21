    @Override
    public Task schedule(final Runnable task, final long delay, final TimeUnit units)
    {
        Timer timer = _timer;
        if (timer == null)
            throw new RejectedExecutionException("STOPPED: " + this);
        SimpleTask t = new SimpleTask(task);
        timer.schedule(t, units.toMillis(delay));
        return t;
    }
