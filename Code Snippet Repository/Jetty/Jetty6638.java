    private void notifyStateListeners(ConnectionState state)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Notify State Listeners: {}",state);
        for (ConnectionStateListener listener : listeners)
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("{}.onConnectionStateChange({})",listener.getClass().getSimpleName(),state.name());
            }
            listener.onConnectionStateChange(state);
        }
    }
