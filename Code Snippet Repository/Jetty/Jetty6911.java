    public void stop()
    {
        LOG.debug("Stopping Server");
        try
        {
            serverSocket.close();
        }
        catch (IOException ignore)
        {
            /* ignore */
        }
    }
