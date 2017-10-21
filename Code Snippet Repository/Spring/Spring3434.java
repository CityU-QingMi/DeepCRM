	@Test
	public void simpleApplicationEventMulticasterWithTaskExecutor() {
		@SuppressWarnings("unchecked")
		ApplicationListener<ApplicationEvent> listener = mock(ApplicationListener.class);
		ApplicationEvent evt = new ContextClosedEvent(new StaticApplicationContext());

		SimpleApplicationEventMulticaster smc = new SimpleApplicationEventMulticaster();
		smc.setTaskExecutor(new Executor() {
			@Override
			public void execute(Runnable command) {
				command.run();
				command.run();
			}
		});
		smc.addApplicationListener(listener);

		smc.multicastEvent(evt);
		verify(listener, times(2)).onApplicationEvent(evt);
	}
