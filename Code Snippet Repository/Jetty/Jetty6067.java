    @Override
    public void removeMessageHandler(MessageHandler handler)
    {
        synchronized (wrappers)
        {
            try
            {
                for (MessageHandlerMetadata metadata : messageHandlerFactory.getMetadata(handler.getClass()))
                {
                    DecoderMetadata decoder = decoderFactory.getMetadataFor(metadata.getMessageClass());
                    MessageType key = decoder.getMessageType();
                    wrappers[key.ordinal()] = null;
                }
                updateMessageHandlerSet();
            }
            catch (IllegalStateException e)
            {
                LOG.warn("Unable to identify MessageHandler: " + handler.getClass().getName(),e);
            }
        }
    }
