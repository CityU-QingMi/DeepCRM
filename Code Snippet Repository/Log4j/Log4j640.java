    @Override
    public void append(final LogEvent event) {
        if (!isStarted()) {
            error("FailoverAppender " + getName() + " did not start successfully");
            return;
        }
        final long localCheckNanos = nextCheckNanos;
        if (localCheckNanos == 0 || System.nanoTime() - localCheckNanos > 0) {
            callAppender(event);
        } else {
            failover(event, null);
        }
    }
