    @Deprecated
    @PluginFactory
    public static SocketAppender createAppender(
            // @formatter:off
            final String host,
            final int port,
            final Protocol protocol,
            final SslConfiguration sslConfig,
            final int connectTimeoutMillis,
            final int reconnectDelayMillis,
            final boolean immediateFail,
            final String name,
            final boolean immediateFlush,
            final boolean ignoreExceptions,
            final Layout<? extends Serializable> layout,
            final Filter filter,
            final boolean advertise,
            final Configuration configuration) {
            // @formatter:on

        // @formatter:off
        return newBuilder()
            .withAdvertise(advertise)
            .setConfiguration(configuration)
            .withConnectTimeoutMillis(connectTimeoutMillis)
            .withFilter(filter)
            .withHost(host)
            .withIgnoreExceptions(ignoreExceptions)
            .withImmediateFail(immediateFail)
            .withLayout(layout)
            .withName(name)
            .withPort(port)
            .withProtocol(protocol)
            .withReconnectDelayMillis(reconnectDelayMillis)
            .withSslConfiguration(sslConfig)
            .build();
        // @formatter:on
    }
