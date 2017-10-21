    public void onPartialTextMessage(String message, boolean fin)
    {
        try
        {
            events.callText(jsrsession.getAsyncRemote(),websocket,message,fin);
        }
        catch (DecodeException e)
        {
            throw new RuntimeException("Unable decode partial text message", e);
        }
    }
