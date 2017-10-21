    @Test
    public void testShutdownCallbackRegistry() throws Exception {
        final LoggerContext context = ctx.getLoggerContext();
        assertTrue("LoggerContext should be started", context.isStarted());
        assertThat(Registry.CALLBACKS, hasSize(1));
        Registry.shutdown();
        assertTrue("LoggerContext should be stopped", context.isStopped());
        assertThat(Registry.CALLBACKS, hasSize(0));
        final ContextSelector selector = ((Log4jContextFactory) LogManager.getFactory()).getSelector();
        assertThat(selector.getLoggerContexts(), not(hasItem(context)));
    }
