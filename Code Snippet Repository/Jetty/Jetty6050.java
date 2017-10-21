    private Wrapper newWrapper(EncoderMetadata metadata)
    {
        Class<? extends Encoder> encoderClass = metadata.getCoderClass();
        try
        {
            Encoder encoder = containerScope.getObjectFactory().createInstance(encoderClass);
            return new Wrapper(encoder,metadata);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            throw new IllegalStateException("Unable to instantiate Encoder: " + encoderClass.getName());
        }
    }
