    @SuppressWarnings("")
    @Override
    public void onConnectionStateChange(ConnectionState state)
    {
        switch (state)
        {
            case CLOSED:
                IOState ioState = this.connection.getIOState();
                CloseInfo close = ioState.getCloseInfo();
                // confirmed close of local endpoint
                notifyClose(close.getStatusCode(),close.getReason());
                try
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("{}.onSessionClosed()",containerScope.getClass().getSimpleName());
                    containerScope.onSessionClosed(this);
                }
                catch (Throwable t)
                {
                    LOG.ignore(t);
                }
                break;
            case CONNECTED:
                // notify session listeners
                try
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("{}.onSessionOpened()",containerScope.getClass().getSimpleName());
                    containerScope.onSessionOpened(this);
                }
                catch (Throwable t)
                {
                    LOG.ignore(t);
                }
                break;
        }
    }
