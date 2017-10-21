    public ServerContainer(NativeWebSocketConfiguration configuration, HttpClient httpClient)
    {
        super(configuration.getFactory(), httpClient);
        this.configuration = configuration;
        EventDriverFactory eventDriverFactory = this.configuration.getFactory().getEventDriverFactory();
        eventDriverFactory.addImplementation(new JsrServerEndpointImpl());
        eventDriverFactory.addImplementation(new JsrServerExtendsEndpointImpl());
        this.configuration.getFactory().addSessionFactory(new JsrSessionFactory(this));
        addBean(this.configuration);
    }
