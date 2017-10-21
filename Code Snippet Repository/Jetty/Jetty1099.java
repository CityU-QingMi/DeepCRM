    protected void notifyPriority(PriorityFrame frame)
    {
        try
        {
            listener.onPriority(frame);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
