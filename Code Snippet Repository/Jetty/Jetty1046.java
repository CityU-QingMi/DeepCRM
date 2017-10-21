    @Override
    public void onShutdown()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Shutting down {}", this);

        switch (closed.get())
        {
            case NOT_CLOSED:
            {
                // The other peer did not send a GO_AWAY, no need to be gentle.
                if (LOG.isDebugEnabled())
                    LOG.debug("Abrupt close for {}", this);
                abort(new ClosedChannelException());
                break;
            }
            case LOCALLY_CLOSED:
            {
                // We have closed locally, and only shutdown
                // the output; now queue a disconnect.
                control(null, Callback.NOOP, new DisconnectFrame());
                break;
            }
            case REMOTELY_CLOSED:
            {
                // Nothing to do, the GO_AWAY frame we
                // received will close the connection.
                break;
            }
            default:
            {
                break;
            }
        }
    }
