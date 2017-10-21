	@Test
	public void lambdaAsListenerWithErrorHandler() {
		final Set<MyEvent> seenEvents = new HashSet<>();
		StaticApplicationContext context = new StaticApplicationContext();
		SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
		multicaster.setErrorHandler(ReflectionUtils::rethrowRuntimeException);
		context.getBeanFactory().registerSingleton(
				StaticApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, multicaster);
		ApplicationListener<MyEvent> listener = seenEvents::add;
		context.addApplicationListener(listener);
		context.refresh();

		MyEvent event1 = new MyEvent(context);
		context.publishEvent(event1);
		context.publishEvent(new MyOtherEvent(context));
		MyEvent event2 = new MyEvent(context);
		context.publishEvent(event2);
		assertSame(2, seenEvents.size());
		assertTrue(seenEvents.contains(event1));
		assertTrue(seenEvents.contains(event2));

		context.close();
	}
