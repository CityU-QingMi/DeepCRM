	@Test
	public void createJmsContainerFullConfig() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		setDefaultJmsConfig(factory);
		factory.setCacheLevel(DefaultMessageListenerContainer.CACHE_CONSUMER);
		factory.setConcurrency("3-10");
		factory.setMaxMessagesPerTask(5);

		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		MessageListener messageListener = new MessageListenerAdapter();
		endpoint.setMessageListener(messageListener);
		endpoint.setDestination("myQueue");
		DefaultMessageListenerContainer container = factory.createListenerContainer(endpoint);

		assertDefaultJmsConfig(container);
		assertEquals(DefaultMessageListenerContainer.CACHE_CONSUMER, container.getCacheLevel());
		assertEquals(3, container.getConcurrentConsumers());
		assertEquals(10, container.getMaxConcurrentConsumers());
		assertEquals(5, container.getMaxMessagesPerTask());

		assertEquals(messageListener, container.getMessageListener());
		assertEquals("myQueue", container.getDestinationName());
	}
