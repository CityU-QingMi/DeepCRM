	@Test
	public void simpleApplicationEventMulticasterWithErrorHandler() {
		@SuppressWarnings("unchecked")
		ApplicationListener<ApplicationEvent> listener = mock(ApplicationListener.class);
		ApplicationEvent evt = new ContextClosedEvent(new StaticApplicationContext());

		SimpleApplicationEventMulticaster smc = new SimpleApplicationEventMulticaster();
		smc.setErrorHandler(TaskUtils.LOG_AND_SUPPRESS_ERROR_HANDLER);
		smc.addApplicationListener(listener);

		willThrow(new RuntimeException()).given(listener).onApplicationEvent(evt);
		smc.multicastEvent(evt);
	}
