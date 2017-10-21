    @Override
    public boolean onIdleTimeout()
    {
        switch (closed.get())
        {
            case NOT_CLOSED:
            {
                long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - idleTime);
                if (elapsed < endPoint.getIdleTimeout())
                    return false;
                return notifyIdleTimeout(this);
            }
            case LOCALLY_CLOSED:
            case REMOTELY_CLOSED:
            {
                abort(new TimeoutException("Idle timeout " + endPoint.getIdleTimeout() + " ms"));
                return false;
            }
            default:
            {
                return false;
            }
        }
    }
