        @Override
        public FlumeAvroManager createManager(final String name, final FactoryData data) {
            try {

                return new FlumeAvroManager(name, data.name, data.agents, data.batchSize, data.delayMillis,
                        data.retries, data.conntectTimeoutMillis, data.requestTimeoutMillis);
            } catch (final Exception ex) {
                LOGGER.error("Could not create FlumeAvroManager", ex);
            }
            return null;
        }
