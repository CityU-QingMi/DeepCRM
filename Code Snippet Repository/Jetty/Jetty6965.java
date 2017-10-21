    private WebSocketServerFactory(ServletContext context, WebSocketPolicy policy, DecoratedObjectFactory objectFactory, Executor executor, ByteBufferPool bufferPool)
    {
        this.context = context;
        this.defaultPolicy = policy;
        this.objectFactory = objectFactory;
        this.executor = executor;
        this.bufferPool = bufferPool;
        
        this.creator = this;
        this.contextClassloader = Thread.currentThread().getContextClassLoader();
        this.eventDriverFactory = new EventDriverFactory(this);
        this.extensionFactory = new WebSocketExtensionFactory(this);

        this.handshakes.put(HandshakeRFC6455.VERSION, new HandshakeRFC6455());
        this.sessionFactories.add(new WebSocketSessionFactory(this));
        
        // Create supportedVersions
        List<Integer> versions = new ArrayList<>();
        for (int v : handshakes.keySet())
        {
            versions.add(v);
        }
        Collections.sort(versions, Collections.reverseOrder()); // newest first
        StringBuilder rv = new StringBuilder();
        for (int v : versions)
        {
            if (rv.length() > 0)
            {
                rv.append(", ");
            }
            rv.append(v);
        }
        supportedVersions = rv.toString();
        
        addBean(scheduler);
        addBean(bufferPool);
    }
