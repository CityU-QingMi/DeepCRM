    @Override
    public RemoteEndpoint getRemote()
    {
        if(LOG_OPEN.isDebugEnabled())
            LOG_OPEN.debug("[{}] {}.getRemote()",policy.getBehavior(),this.getClass().getSimpleName());
        ConnectionState state = connection.getIOState().getConnectionState();

        if ((state == ConnectionState.OPEN) || (state == ConnectionState.CONNECTED))
        {
            return remote;
        }

        throw new WebSocketException("RemoteEndpoint unavailable, current state [" + state + "], expecting [OPEN or CONNECTED]");
    }
