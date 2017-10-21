    @Override
    public void onTextMessage(String message)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("onText({})",message);
        }

        try
        {
            // FIN is always true here
            events.callText(jsrsession.getAsyncRemote(),websocket,message,true);
        }
        catch (Throwable e)
        {
            onFatalError(e);
        }
    }
