        @Override
        public FlumeEmbeddedManager createManager(final String name, final FactoryData data) {
            try {
                final Map<String, String> props = createProperties(data.name, data.agents, data.properties,
                    data.batchSize, data.dataDir);
                final EmbeddedAgent agent = new EmbeddedAgent(name);
                agent.configure(props);
                agent.start();
                LOGGER.debug("Created Agent " + name);
                return new FlumeEmbeddedManager(name, data.name, agent);
            } catch (final Exception ex) {
                LOGGER.error("Could not create FlumeEmbeddedManager", ex);
            }
            return null;
        }
