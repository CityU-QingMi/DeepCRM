    @Before
    public void before() throws Exception {
        blockingAppender = context.getAppender("Block", BlockingAppender.class);
        asyncAppender = context.getAppender("Async", AsyncAppender.class);

        final Field field = AsyncAppender.class.getDeclaredField("asyncQueueFullPolicy");
        field.setAccessible(true);
        policy = new CountingAsyncQueueFullPolicy();
        field.set(asyncAppender, policy);
        policy.queueFull.set(0L);
    }
