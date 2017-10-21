    protected AbstractConnection configure(AbstractConnection connection, Connector connector, EndPoint endPoint)
    {
        connection.setInputBufferSize(getInputBufferSize());

        // Add Connection.Listeners from Connector
        if (connector instanceof ContainerLifeCycle)
        {
            ContainerLifeCycle aggregate = (ContainerLifeCycle)connector;
            for (Connection.Listener listener : aggregate.getBeans(Connection.Listener.class))
                connection.addListener(listener);
        }
        // Add Connection.Listeners from this factory
        for (Connection.Listener listener : getBeans(Connection.Listener.class))
            connection.addListener(listener);

        return connection;
    }
