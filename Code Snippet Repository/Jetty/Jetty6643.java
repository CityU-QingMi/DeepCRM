    public void onCloseRemote(CloseInfo closeInfo)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("onCloseRemote({})", closeInfo);
        ConnectionState event = null;
        synchronized (this)
        {
            if (this.state == ConnectionState.CLOSED)
            {
                // already closed
                return;
            }

            if (LOG.isDebugEnabled())
                LOG.debug("onCloseRemote(), input={}, output={}", inputAvailable, outputAvailable);

            this.closeInfo = closeInfo;

            // turn off further input
            inputAvailable = false;

            if (closeHandshakeSource == CloseHandshakeSource.NONE)
            {
                closeHandshakeSource = CloseHandshakeSource.REMOTE;
            }

            if (!outputAvailable)
            {
                LOG.debug("Close Handshake satisfied, disconnecting");
                cleanClose = true;
                state = ConnectionState.CLOSED;
                finalClose.compareAndSet(null,closeInfo);
                event = this.state;
            }
            else if (this.state == ConnectionState.OPEN)
            {
                // We are now entering CLOSING (or half-closed)
                this.state = ConnectionState.CLOSING;
                event = this.state;
            }
        }

        // Only notify on state change events
        if (event != null)
        {
            notifyStateListeners(event);
        }
    }
