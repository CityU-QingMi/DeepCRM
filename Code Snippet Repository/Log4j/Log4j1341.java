    static Socket createSocket(final String host, int port, int connectTimeoutMillis,
            final SslConfiguration sslConfiguration, SocketOptions socketOptions) throws IOException {
        final SSLSocketFactory socketFactory = createSslSocketFactory(sslConfiguration);
        final SSLSocket socket = (SSLSocket) socketFactory.createSocket();
        if (socketOptions != null) {
            // Not sure which options must be applied before or after the connect() call.
            socketOptions.apply(socket);
        }
        socket.connect(new InetSocketAddress(host, port), connectTimeoutMillis);
        if (socketOptions != null) {
            // Not sure which options must be applied before or after the connect() call.
            socketOptions.apply(socket);
        }
        return socket;
    }
