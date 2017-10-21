    @Override
    public boolean onIdleExpired()
    {
        long idleTimeout = getEndPoint().getIdleTimeout();
        boolean close = delegate.onIdleTimeout(idleTimeout);
        if (multiplexed)
            close &= isFillInterested();
        if (close)
            close(new TimeoutException("Idle timeout " + idleTimeout + " ms"));
        return false;
    }
