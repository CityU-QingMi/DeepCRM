    @Test
    public void testInit()
    {
        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
        Executor executor = new QueuedThreadPool();
        ByteBufferPool bufferPool = new MappedByteBufferPool();
        
        int wsFactoryLevel = setLogLevel(WebSocketServerFactory.class, StdErrLog.LEVEL_DEBUG);
        int abstractLifecycleLevel = setLogLevel(AbstractLifeCycle.class, StdErrLog.LEVEL_DEBUG);
        int containerLifecycleLevel = setLogLevel(ContainerLifeCycle.class, StdErrLog.LEVEL_DEBUG);
        try
        {
            WebSocketServerFactory wsFactory = new WebSocketServerFactory(policy, executor, bufferPool);
            // The above init caused NPE due to bad constructor initialization order with debug active
            assertThat("wsFactory.toString()", wsFactory.toString(), notNullValue());
        }
        finally
        {
            setLogLevel(WebSocketServerFactory.class, wsFactoryLevel);
            setLogLevel(AbstractLifeCycle.class, abstractLifecycleLevel);
            setLogLevel(ContainerLifeCycle.class, containerLifecycleLevel);
        }
    }
