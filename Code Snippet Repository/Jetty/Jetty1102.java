    protected void notifyPushPromise(PushPromiseFrame frame)
    {
        try
        {
            listener.onPushPromise(frame);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
