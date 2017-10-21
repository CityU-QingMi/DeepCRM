    protected static AbstractSocketManager createSocketManager(final String name, Protocol protocol, final String host,
            final int port, final int connectTimeoutMillis, final SslConfiguration sslConfig,
            final int reconnectDelayMillis, final boolean immediateFail, final Layout<? extends Serializable> layout,
            final int bufferSize, final SocketOptions socketOptions) {
        if (protocol == Protocol.TCP && sslConfig != null) {
            // Upgrade TCP to SSL if an SSL config is specified.
            protocol = Protocol.SSL;
        }
        if (protocol != Protocol.SSL && sslConfig != null) {
            LOGGER.info("Appender {} ignoring SSL configuration for {} protocol", name, protocol);
        }
        switch (protocol) {
        case TCP:
            return TcpSocketManager.getSocketManager(host, port, connectTimeoutMillis, reconnectDelayMillis,
                    immediateFail, layout, bufferSize, socketOptions);
        case UDP:
            return DatagramSocketManager.getSocketManager(host, port, layout, bufferSize);
        case SSL:
            return SslSocketManager.getSocketManager(sslConfig, host, port, connectTimeoutMillis, reconnectDelayMillis,
                    immediateFail, layout, bufferSize, socketOptions);
        default:
            throw new IllegalArgumentException(protocol.toString());
        }
    }
