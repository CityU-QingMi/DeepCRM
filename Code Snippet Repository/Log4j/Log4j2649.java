    public synchronized void send(final BatchEvent events) {
        if (rpcClient == null) {
            rpcClient = connect(agents, retries, connectTimeoutMillis, requestTimeoutMillis);
        }

        if (rpcClient != null) {
            try {
                LOGGER.trace("Sending batch of {} events", events.getEvents().size());
                rpcClient.appendBatch(events.getEvents());
            } catch (final Exception ex) {
                rpcClient.close();
                rpcClient = null;
                final String msg = "Unable to write to " + getName() + " at " + agents[current].getHost() + ':' +
                    agents[current].getPort();
                LOGGER.warn(msg, ex);
                throw new AppenderLoggingException("No Flume agents are available");
            }
        }  else {
            final String msg = "Unable to write to " + getName() + " at " + agents[current].getHost() + ':' +
                agents[current].getPort();
            LOGGER.warn(msg);
            throw new AppenderLoggingException("No Flume agents are available");
        }
    }
