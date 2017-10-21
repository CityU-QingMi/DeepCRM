    public ServerConnector(
        @Name("server") Server server,
        @Name("executor") Executor executor,
        @Name("scheduler") Scheduler scheduler,
        @Name("bufferPool") ByteBufferPool bufferPool,
        @Name("acceptors") int acceptors,
        @Name("selectors") int selectors,
        @Name("factories") ConnectionFactory... factories)
    {
        super(server,executor,scheduler,bufferPool,acceptors,factories);
        _manager = newSelectorManager(getExecutor(), getScheduler(),selectors);
        addBean(_manager, true);
        setAcceptorPriorityDelta(-2);
    }
