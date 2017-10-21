    public void send(final Event event) {
        sourceCounter.incrementAppendReceivedCount();
        sourceCounter.incrementEventReceivedCount();
        try {
            getChannelProcessor().processEvent(event);
        } catch (final ChannelException ex) {
            LOGGER.warn("Unabled to process event {}" + event, ex);
            throw ex;
        }
        sourceCounter.incrementAppendAcceptedCount();
        sourceCounter.incrementEventAcceptedCount();
    }
