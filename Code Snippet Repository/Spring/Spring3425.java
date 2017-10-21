	@Test
	public void listenersInApplicationContextWithPayloadEvents() {
		StaticApplicationContext context = new StaticApplicationContext();
		context.registerBeanDefinition("listener", new RootBeanDefinition(MyPayloadListener.class));
		context.refresh();

		MyPayloadListener listener = context.getBean("listener", MyPayloadListener.class);
		context.publishEvent("event1");
		context.publishEvent("event2");
		context.publishEvent("event3");
		context.publishEvent("event4");
		assertTrue(listener.seenPayloads.contains("event1"));
		assertTrue(listener.seenPayloads.contains("event2"));
		assertTrue(listener.seenPayloads.contains("event3"));
		assertTrue(listener.seenPayloads.contains("event4"));

		AbstractApplicationEventMulticaster multicaster = context.getBean(AbstractApplicationEventMulticaster.class);
		assertEquals(2, multicaster.retrieverCache.size());

		context.close();
	}
