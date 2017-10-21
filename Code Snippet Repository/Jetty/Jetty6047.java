    public EncoderMetadata getMetadataFor(Class<?> type)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("getMetadataFor({})",type);
        }
        EncoderMetadata metadata = metadatas.getMetadataByType(type);

        if (metadata != null)
        {
            return metadata;
        }

        if (parentFactory != null)
        {
            return parentFactory.getMetadataFor(type);
        }

        return null;
    }
