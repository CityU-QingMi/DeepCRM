    protected void notifyGoAway(GoAwayFrame frame)
    {
        try
        {
            listener.onGoAway(frame);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
