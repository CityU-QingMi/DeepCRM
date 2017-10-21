        public WriterThread(final Database database, final Environment environment,
                            final FlumePersistentManager manager, final Gate gate, final int batchsize,
                            final SecretKey secretKey, final AtomicLong dbCount, final int lockTimeoutRetryCount) {
            super("FlumePersistentManager-Writer");
            this.database = database;
            this.environment = environment;
            this.manager = manager;
            this.gate = gate;
            this.batchSize = batchsize;
            this.secretKey = secretKey;
            this.setDaemon(true);
            this.dbCounter = dbCount;
            this.lockTimeoutRetryCount = lockTimeoutRetryCount;
        }
