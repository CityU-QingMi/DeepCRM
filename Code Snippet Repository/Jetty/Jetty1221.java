    @Override
    public Connection upgradeConnection(Connector connector, EndPoint endPoint, Request request, HttpFields response101) throws BadMessageException
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} upgraded {}{}", this, request.toString(), request.getFields());

        if (request.getContentLength() > 0)
            return null;

        HTTP2ServerConnection connection = (HTTP2ServerConnection)newConnection(connector, endPoint);
        if (connection.upgrade(request))
            return connection;
        return null;
    }
