    public BasicServerEndpointConfig(WebSocketContainerScope containerScope, ServerEndpointConfig copy)
    {
        // immutable concepts
        this.endpointClass = copy.getEndpointClass();
        this.path = copy.getPath();

        this.decoders = copy.getDecoders();
        this.encoders = copy.getEncoders();
        this.subprotocols = copy.getSubprotocols();
        this.extensions = copy.getExtensions();

        ServerEndpointConfig.Configurator cfgr;

        if (copy.getConfigurator() != null)
        {
            cfgr = copy.getConfigurator();
        }
        else
        {
            cfgr = new ContainerDefaultConfigurator();
        }

        // Make sure all Configurators obtained are decorated
        this.configurator = containerScope.getObjectFactory().decorate(cfgr);

        // mutable concepts
        this.userProperties = new HashMap<>(copy.getUserProperties());
    }
