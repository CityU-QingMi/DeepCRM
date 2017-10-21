    protected boolean handleAcceptFailure(Throwable ex)
    {
        if (isRunning())
        {
            if (ex instanceof InterruptedException)
            {
                LOG.debug(ex);
                return true;
            }

            if (ex instanceof ClosedByInterruptException)
            {
                LOG.debug(ex);
                return false;
            }
            
            LOG.warn(ex);
            try
            {
                // Arbitrary sleep to avoid spin looping.
                // Subclasses may decide for a different
                // sleep policy or closing the connector.
                Thread.sleep(1000);
                return true;
            }
            catch (Throwable x)
            {
                LOG.ignore(x);
            }
            return false;
        }
        else
        {
            LOG.ignore(ex);
            return false;
        }
    }
