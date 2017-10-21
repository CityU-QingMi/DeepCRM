    @Override
    public void onWebSocketText(String message)
    {
        if (isNotConnected())
        {
            return;
        }

        try
        {
            // echo the data back
            RemoteEndpoint remote = getRemote();
            remote.sendString(message);
            if (remote.getBatchMode() == BatchMode.ON)
                remote.flush();
        }
        catch (IOException e)
        {
            throw new RuntimeIOException(e);
        }
    }
