    public WebSocketSession(WebSocketContainerScope containerScope, URI requestURI, EventDriver websocket, LogicalConnection connection)
    {
        Objects.requireNonNull(containerScope,"Container Scope cannot be null");
        Objects.requireNonNull(requestURI,"Request URI cannot be null");

        this.classLoader = Thread.currentThread().getContextClassLoader();
        this.containerScope = containerScope;
        this.requestURI = requestURI;
        this.websocket = websocket;
        this.connection = connection;
        this.executor = connection.getExecutor();
        this.outgoingHandler = connection;
        this.incomingHandler = websocket;
        this.connection.getIOState().addListener(this);
        this.policy = websocket.getPolicy();

        addBean(this.connection);
        addBean(this.websocket);
    }
