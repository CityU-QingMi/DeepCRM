    @Override
    protected boolean onReadTimeout()
    {
        IOState state = getIOState();
        ConnectionState cstate = state.getConnectionState();
        if (LOG_CLOSE.isDebugEnabled())
            LOG_CLOSE.debug("{} Read Timeout - {}",policy.getBehavior(),cstate);

        if (cstate == ConnectionState.CLOSED)
        {
            if (LOG_CLOSE.isDebugEnabled())
                LOG_CLOSE.debug("onReadTimeout - Connection Already CLOSED");
            // close already completed, extra timeouts not relevant
            // allow underlying connection and endpoint to disconnect on its own
            return true;
        }

        try
        {
            notifyError(new SocketTimeoutException("Timeout on Read"));
        }
        finally
        {
            // This is an Abnormal Close condition
            close(StatusCode.SHUTDOWN,"Idle Timeout");
        }

        return false;
    }
