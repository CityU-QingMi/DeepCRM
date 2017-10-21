    @Override
    public Connection newConnection(Connector connector, EndPoint endPoint)
    {
        ServerSessionListener listener = newSessionListener(connector, endPoint);

        Generator generator = new Generator(connector.getByteBufferPool(), getMaxDynamicTableSize(), getMaxHeaderBlockFragment());
        FlowControlStrategy flowControl = getFlowControlStrategyFactory().newFlowControlStrategy();
        HTTP2ServerSession session = new HTTP2ServerSession(connector.getScheduler(), endPoint, generator, listener, flowControl);
        session.setMaxLocalStreams(getMaxConcurrentStreams());
        session.setMaxRemoteStreams(getMaxConcurrentStreams());
        // For a single stream in a connection, there will be a race between
        // the stream idle timeout and the connection idle timeout. However,
        // the typical case is that the connection will be busier and the
        // stream idle timeout will expire earlier than the connection's.
        long streamIdleTimeout = getStreamIdleTimeout();
        if (streamIdleTimeout <= 0)
            streamIdleTimeout = endPoint.getIdleTimeout();
        session.setStreamIdleTimeout(streamIdleTimeout);
        session.setInitialSessionRecvWindow(getInitialSessionRecvWindow());

        ReservedThreadExecutor executor = provideReservedThreadExecutor(connector);
        
        ServerParser parser = newServerParser(connector, session);
        HTTP2Connection connection = new HTTP2ServerConnection(connector.getByteBufferPool(), executor,
                        endPoint, httpConfiguration, parser, session, getInputBufferSize(), listener);
        connection.addListener(connectionListener);
        return configure(connection, connector, endPoint);
    }
