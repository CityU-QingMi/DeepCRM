	@Test
	public void setupJmsMessageContainerFullConfig() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		MessageListener messageListener = new MessageListenerAdapter();
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		endpoint.setDestination("myQueue");
		endpoint.setSelector("foo = 'bar'");
		endpoint.setSubscription("mySubscription");
		endpoint.setConcurrency("5-10");
		endpoint.setMessageListener(messageListener);

		endpoint.setupListenerContainer(container);
		assertEquals("myQueue", container.getDestinationName());
		assertEquals("foo = 'bar'", container.getMessageSelector());
		assertEquals("mySubscription", container.getSubscriptionName());
		assertEquals(5, container.getConcurrentConsumers());
		assertEquals(10, container.getMaxConcurrentConsumers());
		assertEquals(messageListener, container.getMessageListener());
	}
