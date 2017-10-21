    public List<MessageHandlerMetadata> getMetadata(Class<? extends MessageHandler> handler) throws IllegalStateException
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("getMetadata({})",handler);
        }
        List<MessageHandlerMetadata> ret = registered.get(handler);
        if (ret != null)
        {
            return ret;
        }

        return register(handler);
    }
