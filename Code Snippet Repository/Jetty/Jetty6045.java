    @Override
    public void init(EndpointConfig config)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("init({})",config);
        }
        
        if(!containerScope.isRunning())
        {
            throw new RuntimeException(containerScope.getClass().getName() + " is not running yet");
        }
        
        // Instantiate all declared decoders
        for (DecoderMetadata metadata : metadatas)
        {
            Wrapper wrapper = newWrapper(metadata);
            activeWrappers.put(metadata.getObjectType(),wrapper);
        }

        // Initialize all decoders
        for (Wrapper wrapper : activeWrappers.values())
        {
            wrapper.decoder.init(config);
        }
    }
