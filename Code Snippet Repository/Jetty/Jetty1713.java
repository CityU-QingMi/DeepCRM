    @Override
    public Connection customize(Connection connection, Map<String, Object> context)
    {
        if (connection instanceof SslConnection)
        {
            SslConnection sslConnection = (SslConnection)connection;
            sslConnection.setRenegotiationAllowed(sslContextFactory.isRenegotiationAllowed());
            sslConnection.setRenegotiationLimit(sslContextFactory.getRenegotiationLimit());
            sslConnection.setAllowMissingCloseMessage(isAllowMissingCloseMessage());
            ContainerLifeCycle connector = (ContainerLifeCycle)context.get(ClientConnectionFactory.CONNECTOR_CONTEXT_KEY);
            connector.getBeans(SslHandshakeListener.class).forEach(sslConnection::addHandshakeListener);
        }
        return ClientConnectionFactory.super.customize(connection, context);
    }
