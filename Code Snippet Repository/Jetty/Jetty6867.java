    @Override
    public void onConnectionStateChange(ConnectionState state)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Connection State Change: {}",state);
        switch (state)
        {
            case CLOSED:
                this.disconnect();
                break;
            case CLOSING:
                if (ioState.wasRemoteCloseInitiated())
                {
                    // send response close frame
                    CloseInfo close = ioState.getCloseInfo();
                    LOG.debug("write close frame: {}",close);
                    ioState.onCloseLocal(close);
                }
            default:
                break;
        }
    }
