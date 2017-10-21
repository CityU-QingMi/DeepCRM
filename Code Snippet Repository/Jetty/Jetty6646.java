    public void onOpened()
    {
        if(LOG.isDebugEnabled())
            LOG.debug("onOpened()");

        ConnectionState event = null;
        synchronized (this)
        {
            if (this.state == ConnectionState.OPEN)
            {
                // already opened
                return;
            }

            if (this.state != ConnectionState.CONNECTED)
            {
                LOG.debug("Unable to open, not in CONNECTED state: {}",this.state);
                return;
            }

            this.state = ConnectionState.OPEN;
            this.inputAvailable = true;
            this.outputAvailable = true;
            event = this.state;
        }
        notifyStateListeners(event);
    }
