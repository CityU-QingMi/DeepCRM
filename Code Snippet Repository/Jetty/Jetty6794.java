    @OnWebSocketMessage
    public void onText(String message)
    {
        if (session == null)
        {
            // no connection, do nothing.
            // this is possible due to async behavior
            return;
        }

        try
        {
            remote.sendString(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
