    @Override
    protected void doStart() throws Exception
    {
        if (executor == null)
            executor = getServer().getThreadPool();

        if (scheduler == null)
            addBean(scheduler = new ScheduledExecutorScheduler());

        if (bufferPool == null)
            addBean(bufferPool = new MappedByteBufferPool());

        addBean(selector = newSelectorManager());
        selector.setConnectTimeout(getConnectTimeout());

        super.doStart();
    }
