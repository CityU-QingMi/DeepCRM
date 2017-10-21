    public void onConnected()
    {
        ConnectionState event = null;
        synchronized (this)
        {
            if (this.state != ConnectionState.CONNECTING)
            {
                LOG.debug("Unable to set to connected, not in CONNECTING state: {}",this.state);
                return;
            }

            this.state = ConnectionState.CONNECTED;
            inputAvailable = false; // cannot read (yet)
            outputAvailable = true; // write allowed
            event = this.state;
        }
        notifyStateListeners(event);
    }
