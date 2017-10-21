    public void onCloseLocal(CloseInfo closeInfo)
    {
        boolean open = false;
        synchronized (this)
        {
            ConnectionState initialState = this.state;
            if (LOG.isDebugEnabled())
                LOG.debug("onCloseLocal({}) : {}", closeInfo, initialState);
            if (initialState == ConnectionState.CLOSED)
            {
                // already closed
                if (LOG.isDebugEnabled())
                    LOG.debug("already closed");
                return;
            }

            if (initialState == ConnectionState.CONNECTED)
            {
                // fast close. a local close request from end-user onConnect/onOpen method
                if (LOG.isDebugEnabled())
                    LOG.debug("FastClose in CONNECTED detected");
                open = true;
            }
        }

        if (open)
            openAndCloseLocal(closeInfo);
        else
            closeLocal(closeInfo);
    }
