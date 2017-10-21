    public CloseState getCloseState()
    {
        IOState ios = client.getIOState();

        if (ios.wasLocalCloseInitiated())
        {
            return CloseState.LOCAL_INITIATED;
        }
        else if (ios.wasRemoteCloseInitiated())
        {
            return CloseState.REMOTE_INITIATED;
        }
        else
        {
            return CloseState.OPEN;
        }
    }
