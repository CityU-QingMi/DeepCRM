    public void onFailedUpgrade()
    {
        assert (this.state == ConnectionState.CONNECTING);
        ConnectionState event = null;
        synchronized (this)
        {
            this.state = ConnectionState.CLOSED;
            cleanClose = false;
            inputAvailable = false;
            outputAvailable = false;
            event = this.state;
        }
        notifyStateListeners(event);
    }
