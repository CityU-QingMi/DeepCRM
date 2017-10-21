    private void writeMessage(String message)
    {
        if (this.session == null)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Not connected");
            return;
        }

        if (session.isOpen() == false)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Not open");
            return;
        }

        // Async write
        remote.sendText(message);
    }
