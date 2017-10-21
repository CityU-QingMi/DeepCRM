    protected void notifySettings(SettingsFrame frame)
    {
        try
        {
            listener.onSettings(frame);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
