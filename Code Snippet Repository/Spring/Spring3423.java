	private void multicastEvent(boolean match, Class<?> listenerType, ApplicationEvent event, ResolvableType eventType) {
		@SuppressWarnings("unchecked")
		ApplicationListener<ApplicationEvent> listener =
				(ApplicationListener<ApplicationEvent>) mock(listenerType);
		SimpleApplicationEventMulticaster smc = new SimpleApplicationEventMulticaster();
		smc.addApplicationListener(listener);

		if (eventType != null) {
			smc.multicastEvent(event, eventType);
		}
		else {
			smc.multicastEvent(event);
		}
		int invocation = match ? 1 : 0;
		verify(listener, times(invocation)).onApplicationEvent(event);
	}
