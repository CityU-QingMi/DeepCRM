    private boolean closeMessageProducer() {
        if (messageProducer == null) {
            return true;
        }
        final MessageProducer temp = messageProducer;
        messageProducer = null;
        try {
            temp.close();
            return true;
        } catch (final JMSException e) {
            StatusLogger.getLogger().debug(
                    "Caught exception closing JMS MessageProducer: {} ({}); continuing JMS manager shutdown",
                    e.getLocalizedMessage(), temp, e);
            return false;
        }
    }
