        public FactoryData(final String name, final Agent[] agents, final int batchSize, final int retries,
                           final int connectionTimeout, final int requestTimeout, final int delayMillis,
                           final int lockTimeoutRetryCount, final String dataDir, final Property[] properties) {
            this.name = name;
            this.agents = agents;
            this.batchSize = batchSize;
            this.dataDir = dataDir;
            this.retries = retries;
            this.connectionTimeout = connectionTimeout;
            this.requestTimeout = requestTimeout;
            this.delayMillis = delayMillis;
            this.lockTimeoutRetryCount = lockTimeoutRetryCount;
            this.properties = properties;
        }
