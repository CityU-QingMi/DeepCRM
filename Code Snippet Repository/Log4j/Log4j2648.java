    public static FlumeAvroManager getManager(final String name, final Agent[] agents, int batchSize, final int delayMillis,
                                              final int retries, final int connectTimeoutMillis, final int requestTimeoutMillis) {
        if (agents == null || agents.length == 0) {
            throw new IllegalArgumentException("At least one agent is required");
        }

        if (batchSize <= 0) {
            batchSize = 1;
        }

        final StringBuilder sb = new StringBuilder("FlumeAvro[");
        boolean first = true;
        for (final Agent agent : agents) {
            if (!first) {
                sb.append(',');
            }
            sb.append(agent.getHost()).append(':').append(agent.getPort());
            first = false;
        }
        sb.append(']');
        return getManager(sb.toString(), factory,
                new FactoryData(name, agents, batchSize, delayMillis, retries, connectTimeoutMillis, requestTimeoutMillis));
    }
