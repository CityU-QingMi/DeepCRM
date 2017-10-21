    public TcpSocketManager(final String name, final OutputStream os, final Socket socket,
            final InetAddress inetAddress, final String host, final int port, final int connectTimeoutMillis,
            final int reconnectionDelayMillis, final boolean immediateFail, final Layout<? extends Serializable> layout,
            final int bufferSize, final SocketOptions socketOptions) {
        super(name, os, inetAddress, host, port, layout, true, bufferSize);
        this.connectTimeoutMillis = connectTimeoutMillis;
        this.reconnectionDelayMillis = reconnectionDelayMillis;
        this.socket = socket;
        this.immediateFail = immediateFail;
        this.retry = reconnectionDelayMillis > 0;
        if (socket == null) {
            this.reconnector = createReconnector();
            this.reconnector.start();
        }
        this.socketOptions = socketOptions;
    }
