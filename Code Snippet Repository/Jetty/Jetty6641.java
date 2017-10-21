    public void onAbnormalClose(CloseInfo close)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("onAbnormalClose({})",close);
        ConnectionState event = null;
        synchronized (this)
        {
            if (this.state == ConnectionState.CLOSED)
            {
                // already closed
                return;
            }

            if (this.state == ConnectionState.OPEN)
            {
                this.cleanClose = false;
            }

            this.state = ConnectionState.CLOSED;
            finalClose.compareAndSet(null,close);
            this.inputAvailable = false;
            this.outputAvailable = false;
            this.closeHandshakeSource = CloseHandshakeSource.ABNORMAL;
            event = this.state;
        }
        notifyStateListeners(event);
    }
