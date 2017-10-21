    public SimpleContainerScope(WebSocketPolicy policy, ByteBufferPool bufferPool, Executor executor, DecoratedObjectFactory objectFactory)
    {
        this.policy = policy;
        this.bufferPool = bufferPool;
        
        if (objectFactory == null)
        {
            this.objectFactory = new DecoratedObjectFactory();
        }
        else
        {
            this.objectFactory = objectFactory;
        }
        
        if (executor == null)
        {
            QueuedThreadPool threadPool = new QueuedThreadPool();
            String name = "WebSocketContainer@" + hashCode();
            threadPool.setName(name);
            threadPool.setDaemon(true);
            this.executor = threadPool;
            addBean(this.executor);
        }
        else
        {
            this.executor = executor;
        }
    }
