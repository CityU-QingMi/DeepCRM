    public UnixSocketConnector(
        @Name("server") Server server,
        @Name("executor") Executor executor,
        @Name("scheduler") Scheduler scheduler,
        @Name("bufferPool") ByteBufferPool bufferPool,
        @Name("selectors") int selectors,
        @Name("factories") ConnectionFactory... factories)
    {
        super(server,executor,scheduler,bufferPool,0,factories);
        _manager = newSelectorManager(getExecutor(), getScheduler(),
            selectors>0?selectors:1);
        addBean(_manager, true);
        setAcceptorPriorityDelta(-2);
    }
