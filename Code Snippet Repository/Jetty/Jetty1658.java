    protected long checkIdleTimeout()
    {
        if (isOpen())
        {
            long idleTimestamp = getIdleTimestamp();
            long idleTimeout = getIdleTimeout();
            long idleElapsed = System.currentTimeMillis() - idleTimestamp;
            long idleLeft = idleTimeout - idleElapsed;

            if (LOG.isDebugEnabled())
                LOG.debug("{} idle timeout check, elapsed: {} ms, remaining: {} ms", this, idleElapsed, idleLeft);

            if (idleTimestamp != 0 && idleTimeout > 0)
            {
                if (idleLeft <= 0)
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("{} idle timeout expired", this);
                    try
                    {
                        onIdleExpired(new TimeoutException("Idle timeout expired: " + idleElapsed + "/" + idleTimeout + " ms"));
                    }
                    finally
                    {
                        notIdle();
                    }
                }
            }

            return idleLeft >= 0 ? idleLeft : 0;
        }
        return -1;
    }
