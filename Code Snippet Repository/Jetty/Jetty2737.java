    public AbstractConnector(
            Server server,
            Executor executor,
            Scheduler scheduler,
            ByteBufferPool pool,
            int acceptors,
            ConnectionFactory... factories)
    {
        _server=server;
        _executor=executor!=null?executor:_server.getThreadPool();
        if (scheduler==null)
            scheduler=_server.getBean(Scheduler.class);
        _scheduler=scheduler!=null?scheduler:new ScheduledExecutorScheduler();
        if (pool==null)
            pool=_server.getBean(ByteBufferPool.class);
        _byteBufferPool = pool!=null?pool:new ArrayByteBufferPool();

        addBean(_server,false);
        addBean(_executor);
        if (executor==null)
            unmanage(_executor); // inherited from server
        addBean(_scheduler);
        addBean(_byteBufferPool);

        for (ConnectionFactory factory:factories)
            addConnectionFactory(factory);

        int cores = Runtime.getRuntime().availableProcessors();
        if (acceptors < 0)
            acceptors=Math.max(1, Math.min(4,cores/8));
        if (acceptors > cores)
            LOG.warn("Acceptors should be <= availableProcessors: " + this);
        _acceptors = new Thread[acceptors];
    }
