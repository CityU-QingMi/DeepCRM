    @Override
    public void onError(Throwable cause)
    {
        try
        {
            events.callError(websocket,cause);
        }
        catch (Throwable e)
        {
            LOG.warn("Unable to call onError with cause", cause);
            LOG.warn("Call to onError resulted in exception", e);
        }
    }
