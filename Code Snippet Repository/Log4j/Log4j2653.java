        public FactoryData(final String name, final Agent[] agents, final int batchSize, final int delayMillis,
                final int retries, final int connectTimeoutMillis, final int requestTimeoutMillis) {
            this.name = name;
            this.agents = agents;
            this.batchSize = batchSize;
            this.delayMillis = delayMillis;
            this.retries = retries;
            this.conntectTimeoutMillis = connectTimeoutMillis;
            this.requestTimeoutMillis = requestTimeoutMillis;
        }
