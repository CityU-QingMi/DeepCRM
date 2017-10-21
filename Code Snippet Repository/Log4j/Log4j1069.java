    protected boolean stop(final long timeout, final TimeUnit timeUnit, final boolean changeLifeCycleState) {
        if (changeLifeCycleState) {
            this.setStopping();
        }
        boolean stopped = true;
        if (filter != null) {
            if (filter instanceof LifeCycle2) {
                stopped = ((LifeCycle2) filter).stop(timeout, timeUnit);
            } else {
                filter.stop();
                stopped = true;
            }
        }
        if (changeLifeCycleState) {
            this.setStopped();
        }
        return stopped;
    }
