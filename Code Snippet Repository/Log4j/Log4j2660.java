    public static FlumePersistentManager getManager(final String name, final Agent[] agents,
                                                    final Property[] properties, int batchSize, final int retries,
                                                    final int connectionTimeout, final int requestTimeout,
                                                    final int delayMillis, final int lockTimeoutRetryCount,
                                                    final String dataDir) {
        if (agents == null || agents.length == 0) {
            throw new IllegalArgumentException("At least one agent is required");
        }

        if (batchSize <= 0) {
            batchSize = 1;
        }
        final String dataDirectory = Strings.isEmpty(dataDir) ? DEFAULT_DATA_DIR : dataDir;

        final StringBuilder sb = new StringBuilder("FlumePersistent[");
        boolean first = true;
        for (final Agent agent : agents) {
            if (!first) {
                sb.append(',');
            }
            sb.append(agent.getHost()).append(':').append(agent.getPort());
            first = false;
        }
        sb.append(']');
        sb.append(' ').append(dataDirectory);
        return getManager(sb.toString(), factory, new FactoryData(name, agents, batchSize, retries,
            connectionTimeout, requestTimeout, delayMillis, lockTimeoutRetryCount, dataDir, properties));
    }
