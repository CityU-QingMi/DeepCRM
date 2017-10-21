    public Wrapper getWrapperFor(Class<?> type)
    {
        synchronized (activeWrappers)
        {
            Wrapper wrapper = activeWrappers.get(type);

            // Try parent (if needed)
            if ((wrapper == null) && (parentFactory != null))
            {
                wrapper = parentFactory.getWrapperFor(type);
            }

            if (wrapper == null)
            {
                // Attempt to create Wrapper on demand
                DecoderMetadata metadata = metadatas.getMetadataByType(type);
                if (metadata == null)
                {
                    return null;
                }
                wrapper = newWrapper(metadata);
                // track wrapper
                activeWrappers.put(type,wrapper);
            }

            return wrapper;
        }
    }
