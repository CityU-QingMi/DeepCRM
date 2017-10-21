    @Override
    public void onWebSocketText(String message)
    {
        if (isNotConnected())
        {
            return;
        }
        try
        {
            getRemote().sendString(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
