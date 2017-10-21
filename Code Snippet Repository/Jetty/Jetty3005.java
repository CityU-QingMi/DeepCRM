    @Override
    protected AbstractConnection configure(AbstractConnection connection, Connector connector, EndPoint endPoint)
    {
        if (connection instanceof SslConnection)
        {
            SslConnection sslConnection = (SslConnection)connection;
            if (connector instanceof ContainerLifeCycle)
            {
                ContainerLifeCycle container = (ContainerLifeCycle)connector;
                container.getBeans(SslHandshakeListener.class).forEach(sslConnection::addHandshakeListener);
            }
            getBeans(SslHandshakeListener.class).forEach(sslConnection::addHandshakeListener);
        }
        return super.configure(connection, connector, endPoint);
    }
