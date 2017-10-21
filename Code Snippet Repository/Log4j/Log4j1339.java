    public static SslSocketManager getSocketManager(final SslConfiguration sslConfig, final String host, int port,
            final int connectTimeoutMillis, int reconnectDelayMillis, final boolean immediateFail,
            final Layout<? extends Serializable> layout, final int bufferSize, final SocketOptions socketOptions) {
        if (Strings.isEmpty(host)) {
            throw new IllegalArgumentException("A host name is required");
        }
        if (port <= 0) {
            port = DEFAULT_PORT;
        }
        if (reconnectDelayMillis == 0) {
            reconnectDelayMillis = DEFAULT_RECONNECTION_DELAY_MILLIS;
        }
        final String name = "TLS:" + host + ':' + port;
        return (SslSocketManager) getManager(name, new SslFactoryData(sslConfig, host, port, connectTimeoutMillis,
                reconnectDelayMillis, immediateFail, layout, bufferSize, socketOptions), FACTORY);
    }
