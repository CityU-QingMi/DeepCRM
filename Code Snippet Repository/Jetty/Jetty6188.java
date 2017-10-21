    @Override
    public void onWebSocketText(String message)
    {
        if (isNotConnected())
            return;

        try
        {
            RemoteEndpoint remote = getRemote();
            remote.sendString(message, null);
            if (remote.getBatchMode() == BatchMode.ON)
                remote.flush();
        }
        catch (IOException x)
        {
            throw new RuntimeIOException(x);
        }
    }
