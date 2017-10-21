        public BDBWriter(final byte[] keyData, final byte[] eventData, final Environment environment,
                         final Database database, final Gate gate, final AtomicLong dbCount, final long batchSize,
                         final int lockTimeoutRetryCount) {
            this.keyData = keyData;
            this.eventData = eventData;
            this.environment = environment;
            this.database = database;
            this.gate = gate;
            this.dbCount = dbCount;
            this.batchSize = batchSize;
            this.lockTimeoutRetryCount = lockTimeoutRetryCount;
        }
