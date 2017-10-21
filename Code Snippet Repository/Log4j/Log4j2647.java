    protected FlumeAvroManager(final String name, final String shortName, final Agent[] agents, final int batchSize,
                               final int delayMillis, final int retries, final int connectTimeout, final int requestTimeout) {
        super(name);
        this.agents = agents;
        this.batchSize = batchSize;
        this.delayMillis = delayMillis;
        this.delayNanos = TimeUnit.MILLISECONDS.toNanos(delayMillis);
        this.retries = retries;
        this.connectTimeoutMillis = connectTimeout;
        this.requestTimeoutMillis = requestTimeout;
        this.rpcClient = connect(agents, retries, connectTimeout, requestTimeout);
    }
