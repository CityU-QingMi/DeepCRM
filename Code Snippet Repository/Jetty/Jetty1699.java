    public void connectionOpened(Connection connection)
    {
        try
        {
            connection.onOpen();
        }
        catch (Throwable x)
        {
            if (isRunning())
                LOG.warn("Exception while notifying connection " + connection, x);
            else
                LOG.debug("Exception while notifying connection " + connection, x);
            throw x;
        }
    }
