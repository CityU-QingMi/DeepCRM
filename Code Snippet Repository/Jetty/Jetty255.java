    public boolean onIdleTimeout(long idleTimeout)
    {
        synchronized (this)
        {
            if (idleTimeoutGuard == 0)
            {
                long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - idleTimeoutStamp);
                boolean idle = elapsed > idleTimeout / 2;
                if (idle)
                    idleTimeoutGuard = -1;
                if (LOG.isDebugEnabled())
                    LOG.debug("Idle timeout {}/{}ms - {}", elapsed, idleTimeout, this);
                return idle;
            }
            else
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Idle timeout skipped - {}", this);
                return false;
            }
        }
    }
