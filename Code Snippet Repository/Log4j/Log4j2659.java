    protected FlumePersistentManager(final String name, final String shortName, final Agent[] agents,
                                     final int batchSize, final int retries, final int connectionTimeout,
                                     final int requestTimeout, final int delay, final Database database,
                                     final Environment environment, final SecretKey secretKey,
                                     final int lockTimeoutRetryCount) {
        super(name, shortName, agents, batchSize, delay, retries, connectionTimeout, requestTimeout);
        this.database = database;
        this.environment = environment;
        dbCount.set(database.count());
        this.worker = new WriterThread(database, environment, this, gate, batchSize, secretKey, dbCount,
            lockTimeoutRetryCount);
        this.worker.start();
        this.secretKey = secretKey;
        this.threadPool = Executors.newCachedThreadPool(Log4jThreadFactory.createDaemonThreadFactory("Flume"));
        this.lockTimeoutRetryCount = lockTimeoutRetryCount;
    }
