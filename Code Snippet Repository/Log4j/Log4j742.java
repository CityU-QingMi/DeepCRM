        @Override
        public void run() {
            while (!shutdown) {
                try {
                    sleep(configuration.getReconnectIntervalMillis());
                    reconnect();
                } catch (final InterruptedException | JMSException | NamingException e) {
                    LOGGER.debug("Cannot reestablish JMS connection to {}: {}", configuration, e.getLocalizedMessage(),
                            e);
                } finally {
                    latch.countDown();
                }
            }
        }
