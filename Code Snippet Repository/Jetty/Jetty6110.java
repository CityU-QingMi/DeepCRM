    public SimpleEndpointMetadata(Class<? extends Endpoint> endpointClass, EndpointConfig config)
    {
        this.endpointClass = endpointClass;
        this.decoders = new DecoderMetadataSet();
        this.encoders = new EncoderMetadataSet();

        if (config != null)
        {
            this.decoders.addAll(config.getDecoders());
            this.encoders.addAll(config.getEncoders());
        }
    }
