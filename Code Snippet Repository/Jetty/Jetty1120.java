    private void notifyPreface()
    {
        try
        {
            listener.onPreface();
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
