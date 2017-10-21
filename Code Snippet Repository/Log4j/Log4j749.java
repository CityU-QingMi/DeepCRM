    void send(final LogEvent event, final Serializable serializable) {
        if (messageProducer == null) {
            if (reconnector != null && !configuration.isImmediateFail()) {
                reconnector.latch();
            }
            if (messageProducer == null) {
                throw new AppenderLoggingException(
                        "Error sending to JMS Manager '" + getName() + "': JMS message producer not available");
            }
        }
        synchronized (this) {
            try {
                createMessageAndSend(event, serializable);
            } catch (final JMSException causeEx) {
                if (configuration.isRetry() && reconnector == null) {
                    reconnector = createReconnector();
                    try {
                        closeJndiManager();
                        reconnector.reconnect();
                    } catch (NamingException | JMSException reconnEx) {
                        LOGGER.debug("Cannot reestablish JMS connection to {}: {}; starting reconnector thread {}",
                                configuration, reconnEx.getLocalizedMessage(), reconnector.getName(), reconnEx);
                        reconnector.start();
                        throw new AppenderLoggingException(
                                String.format("Error sending to %s for %s", getName(), configuration), causeEx);
                    }
                    try {
                        createMessageAndSend(event, serializable);
                    } catch (final JMSException e) {
                        throw new AppenderLoggingException(
                                String.format("Error sending to %s after reestablishing connection for %s", getName(),
                                        configuration),
                                causeEx);
                    }
                }
            }
        }
    }
