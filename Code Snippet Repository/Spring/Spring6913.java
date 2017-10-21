	@Test
	public void testJcaContainerFactoryConfiguration() {
		Map<String, DefaultJcaListenerContainerFactory> containers =
				context.getBeansOfType(DefaultJcaListenerContainerFactory.class);
		DefaultJcaListenerContainerFactory factory = containers.get("testJcaFactory");
		assertNotNull("No factory registered with testJcaFactory id", factory);

		JmsMessageEndpointManager container =
				factory.createListenerContainer(createDummyEndpoint());
		assertEquals("explicit resource adapter not set",
				context.getBean("testResourceAdapter"),container.getResourceAdapter());
		assertEquals("explicit message converter not set",
				context.getBean("testMessageConverter"), container.getActivationSpecConfig().getMessageConverter());
		assertEquals("Wrong pub/sub", true, container.isPubSubDomain());
		assertEquals("wrong concurrency", 5, container.getActivationSpecConfig().getMaxConcurrency());
		assertEquals("Wrong prefetch", 50, container.getActivationSpecConfig().getPrefetchSize());
		assertEquals("Wrong phase", 77, container.getPhase());
	}
