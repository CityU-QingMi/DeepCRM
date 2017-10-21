    private void replaceConnection()
    {
        EndPoint endPoint = getEndPoint();
        try
        {
            endPoint.upgrade(connectionFactory.newConnection(endPoint, context));
        }
        catch (Throwable x)
        {
            LOG.debug(x);
            close();
        }
    }
