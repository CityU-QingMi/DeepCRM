    private RpcClient connect(final Agent[] agents, int retries, final int connectTimeoutMillis, final int requestTimeoutMillis) {
        try {
            final Properties props = new Properties();

            props.put("client.type", "default_failover");

            int agentCount = 1;
            final StringBuilder sb = new StringBuilder();
            for (final Agent agent : agents) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                final String hostName = "host" + agentCount++;
                props.put("hosts." + hostName, agent.getHost() + ':' + agent.getPort());
                sb.append(hostName);
            }
            props.put("hosts", sb.toString());
            if (batchSize > 0) {
                props.put("batch-size", Integer.toString(batchSize));
            }
            if (retries > 1) {
                if (retries > MAX_RECONNECTS) {
                    retries = MAX_RECONNECTS;
                }
                props.put("max-attempts", Integer.toString(retries * agents.length));
            }
            if (requestTimeoutMillis >= MINIMUM_TIMEOUT) {
                props.put("request-timeout", Integer.toString(requestTimeoutMillis));
            }
            if (connectTimeoutMillis >= MINIMUM_TIMEOUT) {
                props.put("connect-timeout", Integer.toString(connectTimeoutMillis));
            }
            return RpcClientFactory.getInstance(props);
        } catch (final Exception ex) {
            LOGGER.error("Unable to create Flume RPCClient: {}", ex.getMessage());
            return null;
        }
    }
