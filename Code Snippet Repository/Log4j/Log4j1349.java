        public FactoryData(final String host, final int port, final int connectTimeoutMillis,
                final int reconnectDelayMillis, final boolean immediateFail,
                final Layout<? extends Serializable> layout, final int bufferSize, final SocketOptions socketOptions) {
            this.host = host;
            this.port = port;
            this.connectTimeoutMillis = connectTimeoutMillis;
            this.reconnectDelayMillis = reconnectDelayMillis;
            this.immediateFail = immediateFail;
            this.layout = layout;
            this.bufferSize = bufferSize;
            this.socketOptions = socketOptions;
        }
