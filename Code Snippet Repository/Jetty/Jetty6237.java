    public BasicServerEndpointConfig(WebSocketContainerScope containerScope, Class<?> endpointClass, String path)
    {
        this.endpointClass = endpointClass;
        this.path = path;

        this.decoders = new ArrayList<>();
        this.encoders = new ArrayList<>();
        this.subprotocols = new ArrayList<>();
        this.extensions = new ArrayList<>();
        this.userProperties = new HashMap<>();
        this.configurator = new ContainerDefaultConfigurator();
    }
