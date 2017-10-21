    @Override
    public void init(EndpointConfig config)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("init({})",config);
        }

        // Instantiate all declared encoders
        for (EncoderMetadata metadata : metadatas)
        {
            Wrapper wrapper = newWrapper(metadata);
            activeWrappers.put(metadata.getObjectType(),wrapper);
        }

        // Initialize all encoders
        for (Wrapper wrapper : activeWrappers.values())
        {
            wrapper.encoder.init(config);
        }
    }
