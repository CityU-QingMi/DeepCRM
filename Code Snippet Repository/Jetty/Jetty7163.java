    private void writeMessage(String message)
    {
        if (this.session == null)
        {
            LOG.debug("Not connected");
            return;
        }

        if (!session.isOpen())
        {
            LOG.debug("Not open");
            return;
        }

        // Async write
        session.getRemote().sendString(message,null);
    }
