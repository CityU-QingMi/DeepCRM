    @Override
    public synchronized void append(final LogEvent event) {
        final Layout<? extends Serializable> layout = getLayout();
        final byte[] formattedMessage = layout.toByteArray(event);
        if (manager.send(getLayout().toByteArray(event))) {
            sendRcTrue++;
        } else {
            sendRcFalse++;
            LOGGER.error("Appender {} could not send message {} to JeroMQ {}", getName(), sendRcFalse, formattedMessage);
        }
    }
