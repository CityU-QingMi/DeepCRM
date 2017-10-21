    @SuppressWarnings("")
    @Override
    protected void write(final byte[] bytes, final int offset, final int length, final boolean immediateFlush) {
        if (socket == null) {
            if (reconnector != null && !immediateFail) {
                reconnector.latch();
            }
            if (socket == null) {
                throw new AppenderLoggingException("Error writing to " + getName() + ": socket not available");
            }
        }
        synchronized (this) {
            try {
                writeAndFlush(bytes, offset, length, immediateFlush);
            } catch (final IOException causeEx) {
                if (retry && reconnector == null) {
                    final String config = inetAddress + ":" + port;
                    reconnector = createReconnector();
                    try {
                        reconnector.reconnect();
                    } catch (IOException reconnEx) {
                        LOGGER.debug("Cannot reestablish socket connection to {}: {}; starting reconnector thread {}",
                                config, reconnEx.getLocalizedMessage(), reconnector.getName(), reconnEx);
                        reconnector.start();
                        throw new AppenderLoggingException(
                                String.format("Error sending to %s for %s", getName(), config), causeEx);
                    }
                    try {
                        writeAndFlush(bytes, offset, length, immediateFlush);
                    } catch (IOException e) {
                        throw new AppenderLoggingException(
                                String.format("Error writing to %s after reestablishing connection for %s", getName(),
                                        config),
                                causeEx);
                    }
                }
            }
        }
    }
