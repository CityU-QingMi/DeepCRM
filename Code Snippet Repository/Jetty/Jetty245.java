    @Override
    protected void doStart() throws Exception
    {
        if (sslContextFactory != null)
            addBean(sslContextFactory);

        String name = HttpClient.class.getSimpleName() + "@" + hashCode();

        if (executor == null)
        {
            QueuedThreadPool threadPool = new QueuedThreadPool();
            threadPool.setName(name);
            executor = threadPool;
        }
        addBean(executor);
        
        if (byteBufferPool == null)
            byteBufferPool = new MappedByteBufferPool(2048,
                executor instanceof ThreadPool.SizedThreadPool
                    ? ((ThreadPool.SizedThreadPool)executor).getMaxThreads()/2
                    : Runtime.getRuntime().availableProcessors()*2);
        addBean(byteBufferPool);

        if (scheduler == null)
            scheduler = new ScheduledExecutorScheduler(name + "-scheduler", false);
        addBean(scheduler);

        transport.setHttpClient(this);
        addBean(transport);

        if (resolver == null)
            resolver = new SocketAddressResolver.Async(executor, scheduler, getAddressResolutionTimeout());
        addBean(resolver);

        handlers.put(new ContinueProtocolHandler());
        handlers.put(new RedirectProtocolHandler(this));
        handlers.put(new WWWAuthenticationProtocolHandler(this));
        handlers.put(new ProxyAuthenticationProtocolHandler(this));

        decoderFactories.add(new GZIPContentDecoder.Factory(byteBufferPool));

        cookieManager = newCookieManager();
        cookieStore = cookieManager.getCookieStore();

        super.doStart();
    }
