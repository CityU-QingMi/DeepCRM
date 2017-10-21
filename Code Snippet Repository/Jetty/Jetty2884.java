    @Override
    protected void accept(int acceptorID) throws IOException, InterruptedException
    {
        if (LOG.isDebugEnabled())
            LOG.debug("accepting {}", acceptorID);
        LocalEndPoint endPoint = _connects.take();
        endPoint.onOpen();
        onEndPointOpened(endPoint);

        Connection connection = getDefaultConnectionFactory().newConnection(this, endPoint);
        endPoint.setConnection(connection);

        connection.onOpen();
    }
