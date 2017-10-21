    public Wrapper newWrapper(DecoderMetadata metadata)
    {
        Class<? extends Decoder> decoderClass = metadata.getCoderClass();
        try
        {
            Decoder decoder = containerScope.getObjectFactory().createInstance(decoderClass);
            return new Wrapper(decoder,metadata);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            throw new IllegalStateException("Unable to instantiate Decoder: " + decoderClass.getName());
        }
    }
