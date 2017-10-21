    @Deprecated
    public static SocketAppender createAppender(
            // @formatter:off
            final String host,
            final String portNum,
            final String protocolIn,
            final SslConfiguration sslConfig,
            final int connectTimeoutMillis,
            // deprecated
            final String delayMillis,
            final String immediateFail,
            final String name,
            final String immediateFlush,
            final String ignore,
            final Layout<? extends Serializable> layout,
            final Filter filter,
            final String advertise,
            final Configuration config) {
            // @formatter:on
        final boolean isFlush = Booleans.parseBoolean(immediateFlush, true);
        final boolean isAdvertise = Boolean.parseBoolean(advertise);
        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
        final boolean fail = Booleans.parseBoolean(immediateFail, true);
        final int reconnectDelayMillis = AbstractAppender.parseInt(delayMillis, 0);
        final int port = AbstractAppender.parseInt(portNum, 0);
        final Protocol p = protocolIn == null ? Protocol.UDP : Protocol.valueOf(protocolIn);
        return createAppender(host, port, p, sslConfig, connectTimeoutMillis, reconnectDelayMillis, fail, name, isFlush,
                ignoreExceptions, layout, filter, isAdvertise, config);
    }
