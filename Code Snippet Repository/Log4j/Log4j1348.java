    protected static Socket createSocket(final String host, final int port, final SocketOptions socketOptions,
            final int connectTimeoutMillis) throws IOException {
        LOGGER.debug("Creating socket {}:{}", host, port);
        final Socket newSocket = new Socket();
        if (socketOptions != null) {
            // Not sure which options must be applied before or after the connect() call.
            socketOptions.apply(newSocket);
        }
        newSocket.connect(new InetSocketAddress(host, port), connectTimeoutMillis);
        if (socketOptions != null) {
            // Not sure which options must be applied before or after the connect() call.
            socketOptions.apply(newSocket);
        }
        return newSocket;
    }
