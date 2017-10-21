	@Test
	public void simpleApplicationEventMulticasterWithException() {
		@SuppressWarnings("unchecked")
		ApplicationListener<ApplicationEvent> listener = mock(ApplicationListener.class);
		ApplicationEvent evt = new ContextClosedEvent(new StaticApplicationContext());

		SimpleApplicationEventMulticaster smc = new SimpleApplicationEventMulticaster();
		smc.addApplicationListener(listener);

		RuntimeException thrown = new RuntimeException();
		willThrow(thrown).given(listener).onApplicationEvent(evt);
		try {
			smc.multicastEvent(evt);
			fail("Should have thrown RuntimeException");
		}
		catch (RuntimeException ex) {
			assertSame(thrown, ex);
		}
	}
