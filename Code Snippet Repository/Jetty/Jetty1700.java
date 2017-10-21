    public void connectionClosed(Connection connection)
    {
        try
        {
            connection.onClose();
        }
        catch (Throwable x)
        {
            LOG.debug("Exception while notifying connection " + connection, x);
        }
    }
