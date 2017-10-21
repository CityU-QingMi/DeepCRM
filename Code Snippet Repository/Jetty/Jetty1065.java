    @Override
    public void onGoAway(final GoAwayFrame frame)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Received {}", frame);

        while (true)
        {
            CloseState current = closed.get();
            switch (current)
            {
                case NOT_CLOSED:
                {
                    if (closed.compareAndSet(current, CloseState.REMOTELY_CLOSED))
                    {
                        // We received a GO_AWAY, so try to write
                        // what's in the queue and then disconnect.
                        notifyClose(this, frame, new DisconnectCallback());
                        return;
                    }
                    break;
                }
                default:
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Ignored {}, already closed", frame);
                    return;
                }
            }
        }
    }
