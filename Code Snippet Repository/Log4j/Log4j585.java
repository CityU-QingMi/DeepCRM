    protected boolean stop(final Future<?> future) {
        boolean stopped = true;
        if (future != null) {
            if (future.isCancelled() || future.isDone()) {
                return true;
            }
            stopped = future.cancel(true);
        }
        return stopped;
    }
