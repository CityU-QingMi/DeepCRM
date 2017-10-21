	@Test
	public void nonSingletonListenerInApplicationContext() {
		StaticApplicationContext context = new StaticApplicationContext();
		RootBeanDefinition listener = new RootBeanDefinition(MyNonSingletonListener.class);
		listener.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		context.registerBeanDefinition("listener", listener);
		context.refresh();

		MyEvent event1 = new MyEvent(context);
		context.publishEvent(event1);
		MyOtherEvent event2 = new MyOtherEvent(context);
		context.publishEvent(event2);
		MyEvent event3 = new MyEvent(context);
		context.publishEvent(event3);
		MyOtherEvent event4 = new MyOtherEvent(context);
		context.publishEvent(event4);
		assertTrue(MyNonSingletonListener.seenEvents.contains(event1));
		assertTrue(MyNonSingletonListener.seenEvents.contains(event2));
		assertTrue(MyNonSingletonListener.seenEvents.contains(event3));
		assertTrue(MyNonSingletonListener.seenEvents.contains(event4));
		MyNonSingletonListener.seenEvents.clear();

		context.close();
	}
