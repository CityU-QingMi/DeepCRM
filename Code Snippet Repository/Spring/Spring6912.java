	@Test
	public void testJmsContainerFactoryConfiguration() {
		Map<String, DefaultJmsListenerContainerFactory> containers =
				context.getBeansOfType(DefaultJmsListenerContainerFactory.class);
		DefaultJmsListenerContainerFactory factory = containers.get("testJmsFactory");
		assertNotNull("No factory registered with testJmsFactory id", factory);

		DefaultMessageListenerContainer container =
				factory.createListenerContainer(createDummyEndpoint());
		assertEquals("explicit connection factory not set",
				context.getBean(EXPLICIT_CONNECTION_FACTORY), container.getConnectionFactory());
		assertEquals("explicit destination resolver not set",
				context.getBean("testDestinationResolver"), container.getDestinationResolver());
		assertEquals("explicit message converter not set",
				context.getBean("testMessageConverter"), container.getMessageConverter());
		assertEquals("Wrong pub/sub", true, container.isPubSubDomain());
		assertEquals("Wrong durable flag", true, container.isSubscriptionDurable());
		assertEquals("wrong cache", DefaultMessageListenerContainer.CACHE_CONNECTION, container.getCacheLevel());
		assertEquals("wrong concurrency", 3, container.getConcurrentConsumers());
		assertEquals("wrong concurrency", 5, container.getMaxConcurrentConsumers());
		assertEquals("wrong prefetch", 50, container.getMaxMessagesPerTask());
		assertEquals("Wrong phase", 99, container.getPhase());
		assertSame(context.getBean("testBackOff"), new DirectFieldAccessor(container).getPropertyValue("backOff"));
	}
