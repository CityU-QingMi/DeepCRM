    public AnnotatedClientEndpointConfig(ClientEndpoint anno)
    {
        this.decoders = Collections.unmodifiableList(Arrays.asList(anno.decoders()));
        this.encoders = Collections.unmodifiableList(Arrays.asList(anno.encoders()));
        this.preferredSubprotocols = Collections.unmodifiableList(Arrays.asList(anno.subprotocols()));

        // no extensions declared in annotation
        this.extensions = Collections.emptyList();
        // no userProperties in annotation
        this.userProperties = new HashMap<>();

        if (anno.configurator() == null)
        {
            this.configurator = EmptyConfigurator.INSTANCE;
        }
        else
        {
            try
            {
                this.configurator = anno.configurator().newInstance();
            }
            catch (InstantiationException | IllegalAccessException e)
            {
                StringBuilder err = new StringBuilder();
                err.append("Unable to instantiate ClientEndpoint.configurator() of ");
                err.append(anno.configurator().getName());
                err.append(" defined as annotation in ");
                err.append(anno.getClass().getName());
                throw new InvalidWebSocketException(err.toString(),e);
            }
        }
    }
