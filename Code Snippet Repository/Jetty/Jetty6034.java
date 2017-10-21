    protected ClientContainer(final WebSocketContainerScope scope, final HttpClient httpClient)
    {
        String jsr356TrustAll = System.getProperty("org.eclipse.jetty.websocket.jsr356.ssl-trust-all");
        
        WebSocketContainerScope clientScope;
        if (scope.getPolicy().getBehavior() == WebSocketBehavior.CLIENT)
        {
            clientScope = scope;
        }
        else
        {
            // We need to wrap the scope for the CLIENT Policy behaviors
            clientScope = new DelegatedContainerScope(WebSocketPolicy.newClientPolicy(), scope);
        }
    
        this.scopeDelegate = clientScope;
        this.client = new WebSocketClient(scopeDelegate,
                new JsrEventDriverFactory(scopeDelegate),
                new JsrSessionFactory(this),
                httpClient);
        this.client.addBean(httpClient);
    
        if(jsr356TrustAll != null)
        {
            boolean trustAll = Boolean.parseBoolean(jsr356TrustAll);
            client.getSslContextFactory().setTrustAll(trustAll);
        }
        
        this.internalClient = true;
        
        this.endpointClientMetadataCache = new ConcurrentHashMap<>();
        this.decoderFactory = new DecoderFactory(this,PrimitiveDecoderMetadataSet.INSTANCE);
        this.encoderFactory = new EncoderFactory(this,PrimitiveEncoderMetadataSet.INSTANCE);
    }
