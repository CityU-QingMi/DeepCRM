    public WebSocketClient(final WebSocketContainerScope scope, EventDriverFactory eventDriverFactory, SessionFactory sessionFactory, HttpClient httpClient)
    {
        WebSocketContainerScope clientScope;
        if (scope.getPolicy().getBehavior() == WebSocketBehavior.CLIENT)
        {
            clientScope = scope;
        }
        else
        {
            // We need to wrap the scope
            clientScope = new DelegatedContainerScope(WebSocketPolicy.newClientPolicy(), scope);
        }
        
        this.containerScope = clientScope;
        
        if(httpClient == null)
        {
            this.httpClient = HttpClientProvider.get(scope);
            addBean(this.httpClient);
        }
        else
        {
            this.httpClient = httpClient;
        }
        
        this.extensionRegistry = new WebSocketExtensionFactory(containerScope);
        
        this.eventDriverFactory = eventDriverFactory;
        this.sessionFactory = sessionFactory;
    }
