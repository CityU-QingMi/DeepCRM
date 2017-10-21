    @Override
    public synchronized void send(final Event event)  {
        if (batchSize == 1) {
            if (rpcClient == null) {
                rpcClient = connect(agents, retries, connectTimeoutMillis, requestTimeoutMillis);
            }

            if (rpcClient != null) {
                try {
                    rpcClient.append(event);
                } catch (final Exception ex) {
                    rpcClient.close();
                    rpcClient = null;
                    final String msg = "Unable to write to " + getName() + " at " + agents[current].getHost() + ':' +
                            agents[current].getPort();
                    LOGGER.warn(msg, ex);
                    throw new AppenderLoggingException("No Flume agents are available");
                }
            } else {
                final String msg = "Unable to write to " + getName() + " at " + agents[current].getHost() + ':' +
                        agents[current].getPort();
                LOGGER.warn(msg);
                throw new AppenderLoggingException("No Flume agents are available");
            }
        } else {
            batchEvent.addEvent(event);
            final int eventCount = batchEvent.getEvents().size();
            if (eventCount == 1) {
                nextSend = System.nanoTime() + delayNanos;
            }
            if (eventCount >= batchSize || System.nanoTime() >= nextSend) {
                send(batchEvent);
                batchEvent = new BatchEvent();
            }
        }
    }
